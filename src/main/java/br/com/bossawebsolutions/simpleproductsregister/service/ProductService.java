package br.com.bossawebsolutions.simpleproductsregister.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bossawebsolutions.simpleproductsregister.model.Product;
import br.com.bossawebsolutions.simpleproductsregister.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public void save(Product p) {
		productRepository.save(p);
	}
	
	public Product findByName(String name) {
		return productRepository.findByName(name);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	public Product findById(Integer id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
	}

	public void deleteProduct(Product p) {
		productRepository.delete(p);		
	}
}
