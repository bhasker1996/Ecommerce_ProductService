package org.example.ecommerceproductservice.Repository;

import org.example.ecommerceproductservice.Models.Product;
import org.example.ecommerceproductservice.Repository.Projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

     List<Product> findByPriceGreaterThan(Double price);

    List<Product> findProductByTitleLike(String word);
    List<Product> findByTitleIgnoreCase(String word);

    List<Product> findTop5ByTitleContains(String word);

    List<Product> findProductsByTitleAndPriceGreaterThan(String word, Double Price);

    List<Product> findProductsByTitleContainsOrderById(String word);

    Optional<Product> findById(Long Id);

    //HQL deals with Models so thats why "Product"(Captial P)
    //HQL Queries => CAN USE "HQL" QUERIES WITH ANY DATABASE.
    @Query("select p.Id as Id, p.title as title from Product p where p.Id = :x")
    ProductWithIdAndTitle randomSearchMethod(Long x);

    //SQL deals with tables so thats why the table name is product (small p)
    @Query(value = "select * from ECOM_PRODUCT p where p.Id = :productId", nativeQuery = true)
    List<ProductWithIdAndTitle> randomSearchMethod2(Long productId);

}
