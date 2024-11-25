package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import decorators.DiscountDecorator;
import decorators.FastShippingDecorator;
import decorators.GiftwrapDecorator;
import factories.ProductsFactory;
import models.Product;
import strategies.BankTransferPayment;
import strategies.CreditCardPayment;
import strategies.PayPalPayment;
import strategies.PaymentContext;
import strategies.PaymentStrategy;

public class Main {
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
			System.out.println("Id: " + id + "\nTitle: " + title + "\nPrice: " + price + "\nDescription: " + description
					+ "\nCategory: " + category + "\nRating rate: " + p.getRating().getRate() + "\nRating count: "
					+ p.getRating().getCount()
					+ "\n===========================================================================");
		}
	}

	public void addToCart(List<Product> products, List<Product> cart, Scanner input) {
		int choice = 0;
		while (choice != -1) {
			System.out.println("Input product id to add to cart (-1 to exit):");
			choice = input.nextInt();
			input.nextLine();
			if (choice > 0 && choice <= products.size()) {
				Product p = products.get(choice - 1);
				cart.add(p);
				System.out.println(p.getTitle() + " has been added to cart");
			}
		}
	}

	public void orderProducts(List<Product> cart, Scanner input) {
		double amount = 0;
		String choice = "";
		PaymentContext paymentContext = new PaymentContext();
		for (Product p : cart) {
			System.out.println(p.getTitle());
			amount += p.getPrice();
		}
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
			paymentStrategy = new FastShippingDecorator(paymentStrategy, amount);
		}
		paymentContext.setPaymentStrategy(paymentStrategy);
		paymentContext.pay(amount);
		
		//have like a queue for what decorations should be applied first to keep the order ok
	}

	public Main() {
		// TODO Auto-generated constructor stub

//		  PaymentContext paymentContext = new PaymentContext();
//        PaymentStrategy bankTransferPayment = new BankTransferPayment("987654321");
//        PaymentStrategy discountedPayment = new DiscountDecorator(bankTransferPayment, 10.0);
//        PaymentStrategy wrappedPayment = new GiftwrapDecorator(discountedPayment, "RED");
//        PaymentStrategy fastShippingPayment = new FastShippingDecorator(wrappedPayment, yap yap yap);
//        paymentContext.setPaymentStrategy(fastShippingPayment);
//        paymentContext.pay(500.0);
		ProductsFactory factory = new ProductsFactory();
		List<Product> products = factory.createProducts();
		List<Product> cart = new ArrayList<>();
		int choice = 0;
		Scanner input = new Scanner(System.in);
		while (choice != 5) {
			System.out.print(
					"Welcome to FakeStore\n1. View products\n2. Add to cart\n3. View cart\n4. Order products\n>");
			choice = input.nextInt();
			input.nextLine();
			if (choice == 1) {
				viewProducts(products);
			} else if (choice == 2) {
				addToCart(products, cart, input);
			} else if (choice == 3) {
				viewProducts(cart);
			} else if (choice == 4) {
				orderProducts(cart, input);
			}
		}
		input.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
