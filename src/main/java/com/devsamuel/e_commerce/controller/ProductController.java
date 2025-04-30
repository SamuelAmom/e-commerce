package com.devsamuel.e_commerce.controller;

import com.devsamuel.e_commerce.dto.CategoryRequestDTO;
import com.devsamuel.e_commerce.dto.ProductRequestDTO;
import com.devsamuel.e_commerce.dto.ProductResponseDTO;
import com.devsamuel.e_commerce.entity.Category;
import com.devsamuel.e_commerce.entity.Product;
import com.devsamuel.e_commerce.mapper.EntityDtoMapper;
import com.devsamuel.e_commerce.service.CategoryService;
import com.devsamuel.e_commerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final EntityDtoMapper mapper;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, EntityDtoMapper mapper, CategoryService categoryService) {
        this.productService = productService;
        this.mapper = mapper;
        this.categoryService = categoryService;
    }
//--------------------------------------------------------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(mapper.toProductDtoList(products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return productService.findById(id)
                .map(product -> ResponseEntity.ok(mapper.toProductDto(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO requestDTO) {
        Category category = categoryService.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontranda"));
        Product product = mapper.toEntity(requestDTO, category);
        Product savedProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(savedProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO requestDTO) {

        return productService.findById(id)
                .map(existingProduct -> { Category category = categoryService.findById(CategoryRequestDTO.getCategoryId())})
        try {
            Product product = mapper.toProductEntity(requestDTO);
            Product updatedProduct = productService.update(id, product);
            return ResponseEntity.ok(mapper.toProductDto(updatedProduct));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}