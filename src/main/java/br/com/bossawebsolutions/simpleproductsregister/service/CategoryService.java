package br.com.bossawebsolutions.simpleproductsregister.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bossawebsolutions.simpleproductsregister.model.Category;
import br.com.bossawebsolutions.simpleproductsregister.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void save(Category c) {
		categoryRepository.save(c);
	}
	
	public Category findByName(String name) {
		return categoryRepository.findByName(name);
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findById(Integer id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Category not found with id: " + id));
	}

	public void deleteCategory(Category category) {
		categoryRepository.delete(category);
	}

}
