package com.devsamuel.e_commerce.mapper;

import com.devsamuel.e_commerce.dto.*;
import com.devsamuel.e_commerce.entity.Category;
import com.devsamuel.e_commerce.entity.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.List;

@Component
public class EntityDtoMapper {

    //Mapeamento de Category
    public Category toEntity(CategoryRequestDTO dto){
        Category category =  new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }

    public CategoryResponseDTO toDto(Category entity){
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());

        if(entity.getProducts() != null){
            dto.setProducts(entity.getProducts().stream()
                    .map(product -> new ProductSummaryDTO(product.getId(), product.getName()))
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public CategorySummaryDTO toSummaryDto(Category entity){
        return new CategorySummaryDTO(entity.getId(), entity.getName());
    }

    //Mapeamentos de product
    public Product toEntity(ProductRequestDTO dto, Category category) {
        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(category);
        product.setImages(dto.getImages());
        product.setActive(dto.getActive() != null ? dto.getActive() : true);
        return product;
    }

        public void updateEntityFromDto (ProductRequestDTO dto, Product product, Category category){
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setStockQuantity(dto.getStockQuantity());
            product.setCategory(category);
            product.setImages(dto.getImages());
            if (dto.getActive() != null) {
                product.setActive(dto.getActive());
            }
        }

        public ProductResponseDTO toDto (Product entity){

            ProductResponseDTO dto = new ProductResponseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setPrice(entity.getPrice());
            dto.setStockQuantity(entity.getStockQuantity());

            if (entity.getCategory() != null) {
                dto.setCategory(toSummaryDto(entity.getCategory()));
            }

            dto.setImages(entity.getImages());
            dto.setActive(entity.getActive());
            return dto;
        }

        public List<ProductResponseDTO> toProductDtoList(List<Product> products) {
            return products.stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        }

        public List<CategoryResponseDTO> toCategoryDtoList(List<Category> categories) {
            return categories.stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        }
}
