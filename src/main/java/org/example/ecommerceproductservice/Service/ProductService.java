package org.example.ecommerceproductservice.Service;

import org.example.ecommerceproductservice.DTOs.FakeStoreProductDTO;
import org.example.ecommerceproductservice.DTOs.ProductRequestDTO;
import org.example.ecommerceproductservice.DTOs.ProductResponseDTO;
import org.example.ecommerceproductservice.Models.Product;
import java.util.List;
public interface ProductService {

     List<Product>  getAllProducts();
     Product getSingleProduct(Long productId);
     Product addNewProduct(ProductRequestDTO requestDTO);
     Product updateProduct(Long productId, Product product);
     Product replaceProduct(Long productId, Product product);
     Product deleteProduct(Long productId);

}
