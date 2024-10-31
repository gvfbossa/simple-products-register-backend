package br.com.bossawebsolutions.simpleproductsregister.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.bossawebsolutions.simpleproductsregister.model.Category;
import br.com.bossawebsolutions.simpleproductsregister.service.CategoryService;

class CategoryControllerTest {

    private CategoryService categoryServiceMock;
    private CategoryController categoryController;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        categoryServiceMock = mock(CategoryService.class);
        categoryController = new CategoryController();

        Field categoryServiceField = CategoryController.class.getDeclaredField("categoryService");
        categoryServiceField.setAccessible(true);
        categoryServiceField.set(categoryController, categoryServiceMock);
    }

    @Test
    void testGetAllCategories() {
        List<Category> expectedCategories = new ArrayList<>();
        expectedCategories.add(new Category());
        expectedCategories.add(new Category());

        when(categoryServiceMock.findAll()).thenReturn(expectedCategories);

        ResponseEntity<List<Category>> response = categoryController.getAllCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCategories, response.getBody());
    }

    @Test
    void testGetCategoryById_CategoryExists() {
        int categoryId = 1;
        Category expectedCategory = new Category();
        expectedCategory.setId(categoryId);

        when(categoryServiceMock.findById(categoryId)).thenReturn(expectedCategory);

        ResponseEntity<Category> response = categoryController.getCategoryById(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCategory, response.getBody());
    }

    @Test
    void testGetCategoryById_CategoryNotFound() {
        int nonExistentCategoryId = 999;

        when(categoryServiceMock.findById(nonExistentCategoryId)).thenReturn(null);

        ResponseEntity<Category> response = categoryController.getCategoryById(nonExistentCategoryId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
