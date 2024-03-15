package com.example.asset_management.controller;

import com.example.asset_management.entity.Asset;
import com.example.asset_management.entity.Category;
import com.example.asset_management.exception.CategoryManagementException;
import com.example.asset_management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<?> createCategories(@RequestBody List<Category> categories) {
        try {
            List<Category> createdCategories = categoryService.createCategories(categories);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategories);
        } catch (CategoryManagementException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/getCategory")
    public ResponseEntity<List<Category>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        List<Category> categories = categoryService.getAllCategories(page, size);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/id/{categoryId}")
    public Category getCategoryById(@PathVariable Long categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody Category updatedCategory){
        Category category = categoryService.updateCategory(categoryId, updatedCategory);
        if(category != null){
            return ResponseEntity.ok(category);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}

//    @PostMapping("/createCategory")
//    public ResponseEntity<?> createCategories(@RequestBody List<Category> categories) {
//        try {
//            List<Category> createdCategories = categoryService.createCategories(categories);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategories);
//        } catch (CategoryManagementException ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
//    }


//    @PostMapping("/createCategory")
//    public ResponseEntity<?> createCategory(@RequestBody Category category){
////        Category createdCategory = categoryService.createCategory(category);
////        return ResponseEntity.ok(createdCategory);
//        try {
//            Category createdCategory = categoryService.createCategory(category);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
//        } catch (CategoryManagementException ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
//    }

//    public ResponseEntity<List<Category>> getAllCategories() {
//        List<Category> categories = categoryService.getAllCategories(0,10);
//        return ResponseEntity.ok(categories);
//    }