package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.persist.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByTitleLike(String title);
    List<Product> findMaxPrice();
    List<Product> findByTitleOrderByPriceDesc(String title);

@Query("select p from Product p where p.price= ( select MIN (p.price) from Product p) ")
    Product findMinPrice();
}
