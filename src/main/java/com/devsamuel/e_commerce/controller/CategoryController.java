package com.devsamuel.e_commerce.controller;

import com.devsamuel.e_commerce.dto.CategoryRequestDTO;
import com.devsamuel.e_commerce.dto.CategoryResponseDTO;
import com.devsamuel.e_commerce.entity.Category;
import com.devsamuel.e_commerce.mapper.EntityDtoMapper;
import com.devsamuel.e_commerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final EntityDtoMapper mapper;

    public CategoryController(CategoryService categoryService, EntityDtoMapper mapper) {
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(mapper.toCategoryDtoList(categories));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id)
                .map(category -> ResponseEntity.ok(mapper.toDto(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO requestDTO) {
        Category category = mapper.toEntity(requestDTO);
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(savedCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequestDTO requestDTO) {

        return categoryService.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(requestDTO.getName());
                    existingCategory.setDescription(requestDTO.getDescription());
                    Category updated = categoryService.save(existingCategory);
                    return ResponseEntity.ok(mapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
