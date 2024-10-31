package br.com.bossawebsolutions.simpleproductsregister.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.bossawebsolutions.simpleproductsregister.model.Category;
import br.com.bossawebsolutions.simpleproductsregister.repository.CategoryRepository;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepositoryMock;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory() {
        Category category = new Category();
        category.setName("Test Category");
        categoryService.save(category);

        verify(categoryRepositoryMock).save(category);
    }

    @Test
    void testFindCategoryByName() {
        String categoryName = "Test Category";
        Category expectedCategory = new Category();
        expectedCategory.setName(categoryName);

        when(categoryRepositoryMock.findByName(categoryName)).thenReturn(expectedCategory);

        Category actualCategory = categoryService.findByName(categoryName);

        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    void testFindAllCategories() {
        List<Category> expectedCategories = new ArrayList<>();
        expectedCategories.add(new Category());
        expectedCategories.add(new Category());
        expectedCategories.add(new Category());

        when(categoryRepositoryMock.findAll()).thenReturn(expectedCategories);

        List<Category> actualCategories = categoryService.findAll();

        assertEquals(expectedCategories.size(), actualCategories.size());
    }

    @Test
    void testFindCategoryById() {
        int categoryId = 1;
        Category expectedCategory = new Category();
        expectedCategory.setId(categoryId);

        when(categoryRepositoryMock.findById(categoryId)).thenReturn(Optional.of(expectedCategory));

        Category actualCategory = categoryService.findById(categoryId);

        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    void testFindCategoryById_CategoryNotFound() {
        int nonExistentCategoryId = 999;

        when(categoryRepositoryMock.findById(nonExistentCategoryId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> categoryService.findById(nonExistentCategoryId));
    }

    @Test
    void testDeleteCategory() {
        Category category = new Category();
        category.setId(1);

        categoryService.deleteCategory(category);

        verify(categoryRepositoryMock).delete(category);
    }
}
