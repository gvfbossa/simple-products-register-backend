package br.com.bossawebsolutions.simpleproductsregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bossawebsolutions.simpleproductsregister.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public Product findByName(String name);
}
