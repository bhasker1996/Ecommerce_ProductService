package org.example.ecommerceproductservice.Service;

import org.example.ecommerceproductservice.DTOs.ProductRequestDTO;
import org.example.ecommerceproductservice.Exception.ProductNotFoundException;
import org.example.ecommerceproductservice.Mapper.Mapper;
import org.example.ecommerceproductservice.Models.Category;
import org.example.ecommerceproductservice.Models.Product;
import org.example.ecommerceproductservice.Repository.CategoryRepository;
import org.example.ecommerceproductservice.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class SelfProductService implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProducts() {

        List<Product> all = productRepository.findAll();
        return all;
    }

    @Override
    public Product getSingleProduct(Long productId) {

        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty())
        {
            throw new ProductNotFoundException("Product Not found for the given id"+ productId);
        }

        return productOptional.get();
    }

    @Override
    public Product addNewProduct(ProductRequestDTO requestDTO) {
        Product product = Mapper.convertProductRequestDTOtoProduct(requestDTO);
        Category category = product.getCategory();

//        if(category == null)  SINCE DOING CASCADE IN PRODUCT TABLE SO IT WILL CREATE THE CATEGORY ATUTOMATICALLY IN db IF THE CATEGORY IN THE PRODUCT SENT BY CLIENT NOT EXISTS.
//        {
//            Category category1 = categoryRepository.save(category);
//            product.setCategory(category1);
//        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {

        Optional<Product> productOptional = productRepository.findById(productId);

        if(productOptional.isEmpty())
        {
            throw new ProductNotFoundException("product with id:" + productId + "doesn't exists");
        }

        Product productInDB = productOptional.get();

        if(product.getTitle() != null)
        {
            productInDB.setTitle(product.getTitle());
        }

        if(product.getPrice() != null)
        {
            productInDB.setPrice(product.getPrice());
        }

        return productRepository.save(productInDB);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        return null;
    }
}
