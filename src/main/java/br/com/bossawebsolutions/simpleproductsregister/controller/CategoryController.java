package br.com.bossawebsolutions.simpleproductsregister.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bossawebsolutions.simpleproductsregister.model.Category;
import br.com.bossawebsolutions.simpleproductsregister.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
		Category c = categoryService.findById(id);
		
		if (c != null) 
			return new ResponseEntity<>(c, HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Category> addProduct(Category c) {
		Category existingCategory = categoryService.findByName(c.getName());
		if (existingCategory == null) {
			categoryService.save(c);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> updateProduct(@PathVariable Integer id, @RequestBody Category updatedCategory) {
		Category existingCategory = categoryService.findById(id);
		if (existingCategory != null) {
			existingCategory.setName(updatedCategory.getName());
			
			categoryService.save(existingCategory);
			return new ResponseEntity<>(existingCategory, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Category> deleteProduct(@PathVariable Integer id) {
		Category existingCategory = categoryService.findById(id);
		if (existingCategory != null) {
			categoryService.deleteCategory(existingCategory);
			return new ResponseEntity<>(existingCategory, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
