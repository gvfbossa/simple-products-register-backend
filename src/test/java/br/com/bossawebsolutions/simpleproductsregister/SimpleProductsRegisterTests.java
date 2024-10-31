package br.com.bossawebsolutions.simpleproductsregister;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.bossawebsolutions.simpleproductsregister.controller.AuthController;
import br.com.bossawebsolutions.simpleproductsregister.controller.CategoryController;
import br.com.bossawebsolutions.simpleproductsregister.controller.ProductController;
import br.com.bossawebsolutions.simpleproductsregister.service.CategoryService;
import br.com.bossawebsolutions.simpleproductsregister.service.ProductService;
import br.com.bossawebsolutions.simpleproductsregister.service.TokenService;

@SpringBootTest
class SimpleProductsRegisterTests {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private AuthController authController;
	@Autowired
	private ProductController productController;
	@Autowired
	private CategoryController categoryController;

	@Test
	void contextLoads() {
		assertNotNull(categoryService);
		assertNotNull(productService);
		assertNotNull(tokenService);
		assertNotNull(authController);
		assertNotNull(productController);
		assertNotNull(categoryController);
	}

}
