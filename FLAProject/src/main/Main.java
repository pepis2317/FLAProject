package main;

import java.util.List;
import factories.ProductsFactory;
import mementos.CartManager;
import models.MenuFacade;
import models.Product;

public class Main {
	

	public Main() {
		ProductsFactory factory = new ProductsFactory();
		List<Product> products = factory.createProducts();
		CartManager cart = new CartManager();
		MenuFacade menu = new MenuFacade();
		menu.display(products, cart);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
