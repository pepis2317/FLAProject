package adapters;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import models.Product;
import models.Rating;

public class ProductAdapter extends TypeAdapter<Product>{

	@Override
	public void write(JsonWriter out, Product product) throws IOException {
		// TODO Auto-generated method stub
		out.beginObject();
		out.name("id").value(product.getId());
	    out.name("title").value(product.getTitle());
	    out.name("price").value(product.getPrice());
	    out.name("description").value(product.getDescription());
	    out.name("category").value(product.getCategory());
	    out.name("image").value(product.getImage());
	    
	    // Write the nested "rating" object
	    Rating rating = product.getRating();
	    if (rating != null) {
	        out.name("rating");
	        out.beginObject(); // Start the "rating" object
	        out.name("rate").value(rating.getRate());
	        out.name("count").value(rating.getCount());
	        out.endObject(); // End the "rating" object
	    }
		out.endObject();
	}

	@Override
	public Product read(JsonReader in) throws IOException {
		// TODO Auto-generated method stub
		Product product = new Product();
		in.beginObject();
		String fieldName = null;
		while(in.hasNext()) {
			JsonToken token = in.peek();
			if(token.equals(JsonToken.NAME)) {
				fieldName = in.nextName();
			}
			if("id".equals(fieldName)) {
				token = in.peek();
				product.setId(in.nextInt());
			}
			if("title".equals(fieldName)) {
				token = in.peek();
				product.setTitle(in.nextString());
			}
			if("price".equals(fieldName)) {
				token = in.peek();
				product.setPrice(in.nextDouble());
			}
			if("description".equals(fieldName)) {
				token = in.peek();
				product.setDescription(in.nextString());
			}
			if("category".equals(fieldName)) {
				token = in.peek();
				product.setCategory(in.nextString());
			}
			if("image".equals(fieldName)) {
				token = in.peek();
				product.setImage(in.nextString());
			}
			if("rating".equals(fieldName)) {
				Rating rating = new Rating();
	            in.beginObject();
	            while (in.hasNext()) {
	                String ratingField = in.nextName();
	                if ("rate".equals(ratingField)) {
	                    rating.setRate(in.nextDouble());
	                } else if ("count".equals(ratingField)) {
	                    rating.setCount(in.nextInt());
	                }
	            }
	            in.endObject();
	            product.setRating(rating);
			}
		}
		in.endObject();
		return product;
	}

}
