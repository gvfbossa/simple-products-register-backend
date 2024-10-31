package br.com.bossawebsolutions.simpleproductsregister.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer id;
	private String name;
	private String description;
	private BigDecimal price;
	
	@ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
	
	private String categoryPath;
	private boolean available;
	
	public Product() {}
	
	public Product(String name, String description, BigDecimal price, Category category, String categoryPath,
			boolean available) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.categoryPath = categoryPath;
		this.available = available;
	}

}
