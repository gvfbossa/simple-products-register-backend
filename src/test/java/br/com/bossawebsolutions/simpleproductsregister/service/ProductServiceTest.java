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

import br.com.bossawebsolutions.simpleproductsregister.model.Product;
import br.com.bossawebsolutions.simpleproductsregister.repository.ProductRepository;

class ProductServiceTest {

	@Mock
    private ProductRepository productRepositoryMock;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setName("Test Product");

        productService.save(product);

        verify(productRepositoryMock).save(product);
    }

    @Test
    void testFindProductByName() {
        String productName = "Test Product";
        Product expectedProduct = new Product();
        expectedProduct.setName(productName);

        when(productRepositoryMock.findByName(productName)).thenReturn(expectedProduct);

        Product actualProduct = productService.findByName(productName);

        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void testFindAllProducts() {
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product());
        expectedProducts.add(new Product());
        expectedProducts.add(new Product());

        when(productRepositoryMock.findAll()).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.findAll();

        assertEquals(expectedProducts.size(), actualProducts.size());
    }

    @Test
    void testFindProductById() {
        int productId = 1;
        Product expectedProduct = new Product();
        expectedProduct.setId(productId);

        when(productRepositoryMock.findById(productId)).thenReturn(Optional.of(expectedProduct));

        Product actualProduct = productService.findById(productId);

        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void testFindProductById_ProductNotFound() {
        int nonExistentProductId = 999;

        when(productRepositoryMock.findById(nonExistentProductId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> productService.findById(nonExistentProductId));
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setId(1);

        productService.deleteProduct(product);

        verify(productRepositoryMock).delete(product);
    }
}
