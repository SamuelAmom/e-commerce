package com.devsamuel.e_commerce.service.impl;

import com.devsamuel.e_commerce.entity.Category;
import com.devsamuel.e_commerce.exception.ResourceNotFoundException;
import com.devsamuel.e_commerce.repository.CategoryRepository;
import com.devsamuel.e_commerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @Override
    public  Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category){
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category){
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            return categoryRepository.save(category);
        }
        throw new ResourceNotFoundException("Category not found with id: " + id);
    }

    @Override
    public void delete (Long id){
        categoryRepository.deleteById(id);
    }

}
