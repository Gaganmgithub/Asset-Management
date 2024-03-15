package com.example.asset_management.service;

import com.example.asset_management.entity.Category;
import com.example.asset_management.exception.CategoryManagementException;
import com.example.asset_management.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

//    public Category createCategory(Category category){
//        return categoryRepository.save(category);
//    }
        public List<Category> createCategories(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    public List<Category> getAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.getContent();
    }
    public Category getCategoryById(Long categoryId){
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        return categoryOptional.orElseThrow(()-> new CategoryManagementException("category not found : "+ categoryId));
    }

    public Category updateCategory(Long categoryId, Category updatedCategory){
        Optional<Category> existingCategory = categoryRepository.findById(categoryId);
        if(existingCategory.isEmpty()){
            throw new CategoryManagementException("Category not found : "+categoryId);
        }
        Category category = existingCategory.get();
        category.setName(updatedCategory.getName());
        category.setDescription(updatedCategory.getDescription());
//        category.setId(updatedCategory.setId();
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId){
        categoryRepository.deleteById(categoryId);
    }
}




//    public List<Category> createCategories(List<Category> categories) {
//        return categoryRepository.saveAll(categories);
//    }

//    public List<Category> getAllCategories() {
//        return categoryRepository.findAll();
//    }

//    public Category getCategoryByName(String categoryName){
//        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByName(categoryName));
//        return categoryOptional.orElseThrow(()-> new CategoryManagementException("Category not found : "+ categoryName));
//        }
