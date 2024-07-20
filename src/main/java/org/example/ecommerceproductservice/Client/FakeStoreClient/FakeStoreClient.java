package org.example.ecommerceproductservice.Client.FakeStoreClient;

import org.example.ecommerceproductservice.DTOs.FakeStoreProductDTO;
import org.example.ecommerceproductservice.DTOs.ProductRequestDTO;
import org.example.ecommerceproductservice.Exception.ProductNotFoundException;
import org.example.ecommerceproductservice.Mapper.Mapper;
import org.example.ecommerceproductservice.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public FakeStoreClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return (restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables));
    }

    public FakeStoreProductDTO getSingleProduct(Long productId)
      {
          FakeStoreProductDTO forObject = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDTO.class);
          if(forObject == null)
          {
              throw new ProductNotFoundException("product with id" + productId + "doesn't not exists");
          }

          return forObject;
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
