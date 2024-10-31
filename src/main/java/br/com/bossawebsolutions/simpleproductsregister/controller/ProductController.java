package br.com.bossawebsolutions.simpleproductsregister.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bossawebsolutions.simpleproductsregister.model.Product;
import br.com.bossawebsolutions.simpleproductsregister.service.CategoryService;
import br.com.bossawebsolutions.simpleproductsregister.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
		Product p = productService.findById(id);
		
		if (p != null) 
			return new ResponseEntity<>(p, HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product p, Authentication authentication) {
	    if (isAdmin(authentication)) {
	        Product existingProduct = productService.findByName(p.getName());
	        if (existingProduct == null) {
	            p.setCategory(categoryService.findByName(p.getCategory().getName()));
	            
	            productService.save(p);
	            return new ResponseEntity<>(p, HttpStatus.CREATED);
	        }
	        return new ResponseEntity<>(HttpStatus.CONFLICT);
	    }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct, Authentication authentication) {
		if (isAdmin(authentication)) {
	        Product existingProduct = productService.findById(id);
	        if (existingProduct != null) {
	            existingProduct.setName(updatedProduct.getName());
	            existingProduct.setDescription(updatedProduct.getDescription());
	            existingProduct.setPrice(updatedProduct.getPrice());
	            existingProduct.setCategory(updatedProduct.getCategory());
	            existingProduct.setCategoryPath(updatedProduct.getCategoryPath());
	            existingProduct.setAvailable(updatedProduct.isAvailable());
	            
	            productService.save(existingProduct);
	            return new ResponseEntity<>(existingProduct, HttpStatus.OK);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable Integer id, Authentication authentication) {
	    if (isAdmin(authentication)) {
	        Product existingProduct = productService.findById(id);
	        if (existingProduct != null) {
	            productService.deleteProduct(existingProduct);
	            return new ResponseEntity<>(existingProduct, HttpStatus.OK);
	        }
	        return new ResponseEntity<>(existingProduct, HttpStatus.NOT_FOUND);
	    }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	private boolean isAdmin(Authentication authentication) {
	    return authentication.getAuthorities().stream()
	            .anyMatch(role -> role.getAuthority().equals("SCOPE_ROLE_ADMIN"));
	}

}
