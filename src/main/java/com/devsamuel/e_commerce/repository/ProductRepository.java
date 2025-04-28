package com.devsamuel.e_commerce.repository;

import com.devsamuel.e_commerce.entity.Category;
import com.devsamuel.e_commerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Buscar produtos por categoria
    List<Product> findByCategory(Category category);

    // Buscar produtos por nome contendo texto (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Buscar produtos com preço menor que o valor informado
    List<Product> findByPriceLessThan(BigDecimal price);

    // Buscar produtos por ID de categoria
    List<Product> findByCategoryId(Long categoryId);

    // Buscar produtos com estoque maior que a quantidade informada
    List<Product> findByStockQuantityGreaterThan(Integer quantity);

    // Buscar produtos ativos
    List<Product> findByActiveTrue();

    // Usando @Query para consultas mais complexas
   @Query
    List<Product> findProductsByPriceRange(
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice);

}
