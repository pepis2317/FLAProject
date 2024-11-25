package factories;

import java.util.List;
import java.util.stream.Collectors;

import models.Product;
import models.Singleton;

public class ProductsFactory {
	public List<Product> createProducts(){
		Singleton singleton = Singleton.getInstance();
		ProductFactory productFactory = new ProductFactory();
		List<Product> response = singleton.fetch();
		List<Product> products = response.stream()
				.map(p->productFactory.createProduct(
						p.getId(), 
						p.getTitle(), 
						p.getPrice(),
						p.getDescription(), 
						p.getCategory(), 
						p.getImage(), 
						p.getRating()
						)).collect(Collectors.toList());
		return products;
	}
}
