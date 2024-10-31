package br.com.bossawebsolutions.simpleproductsregister.config;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.bossawebsolutions.simpleproductsregister.model.Category;
import br.com.bossawebsolutions.simpleproductsregister.model.Product;
import br.com.bossawebsolutions.simpleproductsregister.service.CategoryService;
import br.com.bossawebsolutions.simpleproductsregister.service.ProductService;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private final ProductService productService;
    @Autowired
    private final CategoryService categoryService;

    private List<String> categories = Arrays.asList("Roupas", "Calçados", "Accessórios");

    public DataLoader(ProductService productService, CategoryService categoryService){
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (String category : categories) {
            if (categoryService.findByName(category) == null)
                categoryService.save(new Category(category));
        }

        initializeProducts();
    }

    private void initializeProducts() {
        initializeProducts("Calça Jeans", "Calça de algodão com 5% de elastano", new BigDecimal("179.99"), categoryService.findByName(categories.get(0)), null, true);
        initializeProducts("Camiseta Preta", "Camiseta de algodão com estampa", new BigDecimal("59.99"), categoryService.findByName(categories.get(0)), null, true);
        initializeProducts("Vestido de Festa", "Vestido azul longo com paetês", new BigDecimal("229.79"), categoryService.findByName(categories.get(0)), null, true);
        initializeProducts("Jaqueta de Couro", "Jaqueta de couro legítimo", new BigDecimal("599.99"), categoryService.findByName(categories.get(0)), null, true);

        initializeProducts("Tênis Esportivo", "Tênis para corrida e prática de esportes", new BigDecimal("379.99"), categoryService.findByName(categories.get(1)), null, true);
        initializeProducts("Sapato Scarpin", "Sapato feminino clássico na cor vermelha", new BigDecimal("119.99"), categoryService.findByName(categories.get(1)), null, true);
        initializeProducts("Chinelos de Borracha", "Chinelos para uso diário em qualquer ocasião", new BigDecimal("39.99"), categoryService.findByName(categories.get(1)), null, true);
        initializeProducts("Sapato Social", "Sapatos masculinos na cor marrom", new BigDecimal("69.99"), categoryService.findByName(categories.get(1)), null, true);

        initializeProducts("Cinto de Couro", "Cinto de fivela dourada na cor preta", new BigDecimal("29.99"), categoryService.findByName(categories.get(2)), null, true);
        initializeProducts("Bolsa Feminina Discreta", "Bolsa pequena com alça de corrente", new BigDecimal("89.99"), categoryService.findByName(categories.get(2)), null, true);
        initializeProducts("Relógio Analógico", "Um clássico atemporal, relógio de ponteiros", new BigDecimal("299.99"), categoryService.findByName(categories.get(2)), null, true);
        initializeProducts("Boné de Time", "Boné com a estampa do seu time favorito", new BigDecimal("39.99"), categoryService.findByName(categories.get(2)), null, true);
    }

    private void initializeProducts(String name, String desc, BigDecimal price, Category cat, String catPath, boolean available) {
        if (productService.findByName(name) == null)
            productService.save(new Product(name, desc, price, cat, catPath, available));
    }

    
}
