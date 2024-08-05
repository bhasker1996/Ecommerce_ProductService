package org.example.ecommerceproductservice.Service;

import org.example.ecommerceproductservice.Client.FakeStoreClient.FakeStoreClient;
import org.example.ecommerceproductservice.DTOs.FakeStoreProductDTO;
import org.example.ecommerceproductservice.DTOs.ProductRequestDTO;
import org.example.ecommerceproductservice.DTOs.ProductResponseDTO;
import org.example.ecommerceproductservice.Exception.ProductNotFoundException;
import org.example.ecommerceproductservice.Mapper.Mapper;
import org.example.ecommerceproductservice.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService
{

    private FakeStoreClient fakeStoreClient;

    @Autowired
    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public Page<Product> getAllProducts(int pageNo, int size) {

        List<FakeStoreProductDTO> allProducts = fakeStoreClient.getAllProducts();

        List<Product> products = allProducts.stream().map(x -> Mapper.convertFakeStoreProductDTOtoProduct(x)).collect(Collectors.toList());
        //return new PageImpl<>(products);
        return new PageImpl<>(products);
    }

    @Override //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public Product getSingleProduct(Long productId) {

        FakeStoreProductDTO singleProduct = fakeStoreClient.getSingleProduct(productId);
        return Mapper.convertFakeStoreProductDTOtoProduct(singleProduct);
    }

    @Override  //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public Product addNewProduct(ProductRequestDTO requestDTO) {
        FakeStoreProductDTO responseDTO = fakeStoreClient.addNewProduct(requestDTO);
        return Mapper.convertFakeStoreProductDTOtoProduct(responseDTO);
    }

    @Override  //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public Product updateProduct(Long productId, Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreClient.updateProduct(productId, product);
        return Mapper.convertFakeStoreProductDTOtoProduct(fakeStoreProductDTO);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreClient.replaceProduct(productId, product);
        return Mapper.convertFakeStoreProductDTOtoProduct(fakeStoreProductDTO);
    }

    @Override
    public Product deleteProduct(Long productId) {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreClient.deleteProduct(productId);
        return Mapper.convertFakeStoreProductDTOtoProduct(fakeStoreProductDTO);
    }
}
