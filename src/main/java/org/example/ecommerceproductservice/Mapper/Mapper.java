package org.example.ecommerceproductservice.Mapper;

import org.example.ecommerceproductservice.DTOs.FakeStoreProductDTO;
import org.example.ecommerceproductservice.DTOs.ProductRequestDTO;
import org.example.ecommerceproductservice.Models.Category;
import org.example.ecommerceproductservice.Models.Product;

public class Mapper {

    public static Product convertFakeStoreProductDTOtoProduct(FakeStoreProductDTO fakeStoreProductDTO)
    {
        Product product = new Product();
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setImageURL(fakeStoreProductDTO.getImage());

        Category category = new Category();
        category.setName(fakeStoreProductDTO.getCategory());

        product.setCategory(category);
        return product;
    }

    public static Product convertProductRequestDTOtoProduct(ProductRequestDTO requestDTO)
    {
        Product product = new Product();
        product.setTitle(requestDTO.getTitle());
        product.setPrice(requestDTO.getPrice());
        product.setImageURL(requestDTO.getImageURL());
        product.setDescription(requestDTO.getDescription());
        Category category = new Category();
        category.setName(requestDTO.getCategory().getName());
       category.setDescription(requestDTO.getCategory().getDescription());
        product.setCategory(category);

        return product;
    }

    public static FakeStoreProductDTO convertProductToFakeStoreProductDTO(Product product)
    {
        FakeStoreProductDTO requestDTO = new FakeStoreProductDTO();
        requestDTO.setTitle(product.getTitle());
        requestDTO.setPrice(product.getPrice());
        requestDTO.setImage(product.getImageURL());
        requestDTO.setDescription(product.getDescription());
        requestDTO.setCategory(product.getCategory().getName());
        return requestDTO;
    }

}
