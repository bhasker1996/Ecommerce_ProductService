package org.example.ecommerceproductservice.Client.FakeStoreClient;

import org.example.ecommerceproductservice.DTOs.FakeStoreProductDTO;
import org.example.ecommerceproductservice.DTOs.ProductRequestDTO;
import org.example.ecommerceproductservice.Exception.ProductNotFoundException;
import org.example.ecommerceproductservice.Mapper.Mapper;
import org.example.ecommerceproductservice.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreClient {

    private RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public FakeStoreClient(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return (restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables));
    }

    public FakeStoreProductDTO getSingleProduct(Long productId)
      {
//          FakeStoreProductDTO forObject = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDTO.class);
//          if(forObject == null)
//          {
//              throw new ProductNotFoundException("product with id" + productId + "doesn't not exists");
//          }
//
//          return forObject;


            //Try to fetch product from redis.
          Product product = (Product) redisTemplate.opsForHash().get("PRODUCT", "PRODUCT_" + productId);

          if(product != null)
          {
              //cache HIT
              return Mapper.convertProductToFakeStoreProductDTO(product);
          }

          //Call FakeStore to fetch the Product with given id. => HTTP Call.

          FakeStoreProductDTO fakeStoreProductDto = restTemplate.getForObject(
                  "https://fakestoreapi.com/products/" + productId,
                  FakeStoreProductDTO.class
          );

          if (fakeStoreProductDto == null) {
              throw new ProductNotFoundException("Product with id " + productId + " doesn't exist");
          }

          //Convert FakeStoreProductDto into Product.
          //Cache MISS
          Product product1 = Mapper.convertFakeStoreProductDTOtoProduct(fakeStoreProductDto);

          //Store the product in redis.
          redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + productId, product1);

          return fakeStoreProductDto;
      }


      // Adding New Product to fakestoreclient
    public FakeStoreProductDTO addNewProduct(ProductRequestDTO requestDTO)
    {
        ResponseEntity<FakeStoreProductDTO> responseDTO = restTemplate.postForEntity("https://fakestoreapi.com/products", requestDTO, FakeStoreProductDTO.class);
        return responseDTO.getBody();
    }


    //Getting All Products
    public List<FakeStoreProductDTO> getAllProducts()
    {
        ResponseEntity<FakeStoreProductDTO[]> responseArray = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        return List.of(responseArray.getBody());
    }

    //Updating a product
    public FakeStoreProductDTO updateProduct(Long productId, Product product)
    {
        FakeStoreProductDTO requestDTO = Mapper.convertProductToFakeStoreProductDTO(product);
        ResponseEntity<FakeStoreProductDTO> responseDTO = requestForEntity(HttpMethod.PATCH, "https://fakestoreapi.com/products/{id}", requestDTO, FakeStoreProductDTO.class, productId);
        return responseDTO.getBody();
    }

    //Deleting a product
    public FakeStoreProductDTO deleteProduct(Long productId)
    {
        ResponseEntity<FakeStoreProductDTO> response = requestForEntity(HttpMethod.DELETE,
                "https://fakestoreapi.com/products/{id}",
                null, FakeStoreProductDTO.class, productId);
        return response.getBody();
    }

    public FakeStoreProductDTO replaceProduct(Long productId, Product product) {

        FakeStoreProductDTO requestDTO = Mapper.convertProductToFakeStoreProductDTO(product);

        ResponseEntity<FakeStoreProductDTO> responseDTO = requestForEntity(HttpMethod.PUT, "https://fakestoreapi.com/products", requestDTO, FakeStoreProductDTO.class, productId);
        return responseDTO.getBody();
    }
}
