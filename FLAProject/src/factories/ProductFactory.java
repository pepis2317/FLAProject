package factories;

import models.Product;
import models.Rating;

class ProductFactory {
	protected Product createProduct(int id, String title, double price, String description, String category, String image, Rating rating) {
		//Gson handles the creation just fine, this factory is beneficial in validation and setting default values
		if(id<0) {
			throw new IllegalArgumentException("Product Id must be a non negative integer");
		}
		if(title.isBlank() || title == null) {
			title = "No product title given";
		}
		if (price < 0) {
            price = 0;
        }
		if(description.isBlank() || description == null) {
			description = "No product description given";
		}
		if(category.isBlank() || category == null) {
			category = "No product category given";
		}
		if(image.isBlank() || image == null) {
			image = "No product image given";
		}
		if(rating == null) {
			rating = new Rating(0, 0);
		}else {
			if(rating.getRate() < 0) {
				rating.setRate(0);
			}
			if(rating.getCount() < 0) {
				rating.setCount(0);
			}
		}
		return new Product(id, title, price, description, category, image,rating);
	}
}
