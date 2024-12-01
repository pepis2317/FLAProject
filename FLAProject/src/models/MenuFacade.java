package models;

import java.util.List;
import java.util.Scanner;

import decorators.DiscountDecorator;
import decorators.FastShippingDecorator;
import decorators.GiftwrapDecorator;
import mementos.CartManager;
import strategies.BankTransferPayment;
import strategies.CreditCardPayment;
import strategies.PayPalPayment;
import strategies.PaymentContext;
import strategies.PaymentStrategy;

public class MenuFacade {
	public String shorten(String s) {
		if (s.length() > 30) {
			return s.substring(0, 29);
		}
		return s;
	}
	public void viewProducts(List<Product> products) {
		for (Product p : products) {
			int id = p.getId();
			String title = shorten(p.getTitle());
			double price = p.getPrice();
			String description = shorten(p.getDescription());
			String category = shorten(p.getDescription());
			System.out.println(
					"Id: " + id 
					+ "\nTitle: " + title 
					+ "\nPrice: " + price 
					+ "\nDescription: " + description
					+ "\nCategory: " + category 
					+ "\nRating rate: " + p.getRating().getRate() 
					+ "\nRating count: "+ p.getRating().getCount()
					+ "\n===========================================================================");
		}
	}
	public void orderProducts(CartManager cart, Scanner input) {
		double amount = cart.getTotalAmount();
		String choice = "";
		PaymentContext paymentContext = new PaymentContext();
		viewProducts(cart.getCart());
		System.out.println("Total order: " + amount);
		while (!choice.equals("bank") && !choice.equals("credit") && !choice.equals("paypal")) {
			System.out.println("Payment method? (bank || credit || paypal)");
			choice = input.nextLine();
		}
		PaymentStrategy paymentStrategy = null;
		if (choice.equals("bank")) {
			paymentStrategy = new BankTransferPayment("1234567");
		} else if (choice.equals("credit")) {
			paymentStrategy = new CreditCardPayment("1234567", "user");
		} else if (choice.equals("paypal")) {
			paymentStrategy = new PayPalPayment("user@mail.com");
		}
		choice = "";
		while(!choice.equals("Y") && !choice.equals("N")) {
			System.out.println("Use Discount? (Y/N)");
			choice = input.nextLine();
		}
		
		if (choice.equals("Y")) {
			paymentStrategy = new DiscountDecorator(paymentStrategy, 10);	
		}
		
		choice = "";
		while(!choice.equals("Y") && !choice.equals("N")) {
			System.out.println("Use giftwrap on order? (Y/N)");
			choice = input.nextLine();
		}
		
		if (choice.equals("Y")) {
			paymentStrategy = new GiftwrapDecorator(paymentStrategy, "RED");
		}
		choice = "";
		while(!choice.equals("Y") && !choice.equals("N")) {
			System.out.println("Use FastShipping? (Y/N)");
			choice = input.nextLine();
		}
		
		if (choice.equals("Y")) {
			paymentStrategy = new FastShippingDecorator(paymentStrategy);
		}
		
		paymentContext.setPaymentStrategy(paymentStrategy);
		paymentContext.pay(amount);
		cart.clearCart();
	
	}
	public void display(List<Product> products, CartManager cart) {
		int choice = 0;
		Scanner input = new Scanner(System.in);
		while (choice != 10) {
			System.out.print(
					"Welcome to FakeStore\n"
					+ "1. View products\n"
					+ "2. View cart\n"
					+ "3. Add product to cart\n"
					+ "4. Remove product from cart\n"
					+ "5. Undo addition/removal\n"
					+ "6. Redo addition/removal\n"
					+ "7. Order products\n"
					+ ">");
			choice = input.nextInt();
			input.nextLine();
			if (choice == 1) {
				viewProducts(products);
			} else if (choice == 2) {
				viewProducts(cart.getCart());
			} else if (choice == 3) {
				cart.addProduct(products, input);
			} else if (choice == 4) {
				cart.removeProduct(input);
			}else if (choice == 5) {
				cart.undo();
			}else if (choice == 6) {
				cart.redo();
			}else if (choice == 7) {
				orderProducts(cart,input);
				
			}
		}
		input.close();
	
	}
}
