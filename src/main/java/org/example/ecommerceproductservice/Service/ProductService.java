package org.example.ecommerceproductservice.Service;

import org.example.ecommerceproductservice.DTOs.FakeStoreProductDTO;
import org.example.ecommerceproductservice.DTOs.ProductRequestDTO;
import org.example.ecommerceproductservice.DTOs.ProductResponseDTO;
import org.example.ecommerceproductservice.Models.Product;
import org.springframework.data.domain.Page;

import java.util.List;
public interface ProductService {

     Page<Product> getAllProducts(int pageNo, int size);
     Product getSingleProduct(Long productId);
     Product addNewProduct(ProductRequestDTO requestDTO);
     Product updateProduct(Long productId, Product product);
     Product replaceProduct(Long productId, Product product);
     Product deleteProduct(Long productId);

}
