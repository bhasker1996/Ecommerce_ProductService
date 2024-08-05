package org.example.ecommerceproductservice.Controllers;

import org.example.ecommerceproductservice.DTOs.ProductRequestDTO;
import org.example.ecommerceproductservice.Mapper.Mapper;
import org.example.ecommerceproductservice.Models.Product;
import org.example.ecommerceproductservice.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("SelfProductService") ProductService productService) {
        this.productService = productService;
    }


    //Getting Single Product >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long productId)
    {
        return ResponseEntity.ok(productService.getSingleProduct(productId));
    }


    //Adding New Product >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @PostMapping
    public ResponseEntity addNewProduct(@RequestBody ProductRequestDTO requestDTO)
    {
        Product product = productService.addNewProduct(requestDTO);
        return ResponseEntity.ok(product);
    }


    //Getting All Products >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @GetMapping()
    public ResponseEntity getAllProducts(@RequestParam("pageNo") int pageNo, @RequestParam("size") int size)
    {
        Page<Product> allProducts = productService.getAllProducts(pageNo,size);
        return ResponseEntity.ok(allProducts);
    }


    //Updating a Product  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @PatchMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") Long productId, @RequestBody ProductRequestDTO requestDTO)
    {
        Product product = Mapper.convertProductRequestDTOtoProduct(requestDTO);
        Product product1 = productService.updateProduct(productId, product);
        return ResponseEntity.ok(product1);
    }

    //Replacing a product >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @PutMapping("/{id}")
    public ResponseEntity replaceProduct(@PathVariable("id") Long productId, @RequestBody ProductRequestDTO requestDTO)
    {
        Product product = Mapper.convertProductRequestDTOtoProduct(requestDTO);
        Product product1 = productService.replaceProduct(productId, product);
        return ResponseEntity.ok(product1);
    }


    //Deleting a product >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long productId)
    {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }


}
