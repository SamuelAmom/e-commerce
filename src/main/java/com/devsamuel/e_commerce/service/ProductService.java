package com.devsamuel.e_commerce.service;

import com.devsamuel.e_commerce.entity.Product;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Product update(Long id, Product product);

    void delete(Long id);


    //Métodos de negócio específicos
    List<Product> findByCategory(Long categoryId);

    List<Product> findByNameContaining(String Name);

    List<Product> findByPriceRange(BigDecimal miniPrice, BigDecimal maxPrice);

    List<Product> findByStock();

    boolean updateStock(Long productId, Integer quantity);
}