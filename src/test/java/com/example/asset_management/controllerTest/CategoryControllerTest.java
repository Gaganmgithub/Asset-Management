package com.example.asset_management.controllerTest;


import com.example.asset_management.controller.CategoryController;
import com.example.asset_management.entity.Category;
import com.example.asset_management.exception.CategoryManagementException;
import com.example.asset_management.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

    @RunWith(MockitoJUnitRunner.class)
    public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Before
    public void setUp() {

        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Category 1");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Category 2");

        when(categoryService.getAllCategories(0, 5)).thenReturn(Arrays.asList(category1, category2));
        when(categoryService.getCategoryById(1L)).thenReturn(category1);

    }

    @Test
    public void testGetAllCategories() {
        ResponseEntity<List<Category>> response = categoryController.getAllCategories(0, 5);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetCategoryById() {
        Category response = categoryController.getCategoryById(1L);
        assertEquals("Category 1", response.getName());
    }

    @Test
    public void testCreateCategory() throws CategoryManagementException {
//        Category category = new Category();
//        category.setId(3L);
//        category.setName("Category 3");
//
//        when(categoryService.createCategory(category)).thenReturn(category);
//
//        ResponseEntity<?> response = categoryController.createCategory(category);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());

//        public void testCreateCategories() {//throws CategoryManagementException {
            // Mock data setup
            Category category1 = new Category();
            category1.setName("Category 1");

            Category category2 = new Category();
            category2.setName("Category 2");

            List<Category> categoriesToCreate = Arrays.asList(category1, category2);

            when(categoryService.createCategories(categoriesToCreate)).thenReturn(categoriesToCreate);

            // Test
            ResponseEntity<?> response = categoryController.createCategories(categoriesToCreate);
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
        }

}