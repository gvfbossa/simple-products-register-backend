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

import br.com.bossawebsolutions.simpleproductsregister.model.Product;
import br.com.bossawebsolutions.simpleproductsregister.service.CategoryService;
import br.com.bossawebsolutions.simpleproductsregister.service.ProductService;

class ProductControllerTest {

    private ProductService productServiceMock;
    private CategoryService categoryServiceMock;
    private ProductController productController;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        productServiceMock = mock(ProductService.class);
        categoryServiceMock = mock(CategoryService.class);
        productController = new ProductController();

        Field productServiceField = ProductController.class.getDeclaredField("productService");
        productServiceField.setAccessible(true);
        productServiceField.set(productController, productServiceMock);

        Field categoryServiceField = ProductController.class.getDeclaredField("categoryService");
        categoryServiceField.setAccessible(true);
        categoryServiceField.set(productController, categoryServiceMock);
    }

    @Test
    void testGetAllProducts() {
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product());
        expectedProducts.add(new Product());

        when(productServiceMock.findAll()).thenReturn(expectedProducts);

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedProducts, response.getBody());
    }

    @Test
    void testGetProductById_ProductExists() {
        int productId = 1;
        Product expectedProduct = new Product();
        expectedProduct.setId(productId);

        when(productServiceMock.findById(productId)).thenReturn(expectedProduct);

        ResponseEntity<Product> response = productController.getProductById(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedProduct, response.getBody());
    }

    @Test
    void testGetProductById_ProductNotFound() {
        int nonExistentProductId = 999;

        when(productServiceMock.findById(nonExistentProductId)).thenReturn(null);

        ResponseEntity<Product> response = productController.getProductById(nonExistentProductId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
