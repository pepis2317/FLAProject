package mementos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Product;

public class CartManager {
	private List<Product> cart = new ArrayList<>();
	private double totalAmount = 0;
	Caretaker caretaker = new Caretaker();
	public void addProduct(List<Product> products, Scanner input) {
		int choice = 0;
		while (choice != -1) {
			System.out.println("Input product id to add to cart (-1 to exit):");
			choice = input.nextInt();
			input.nextLine();
			if (choice > 0 && choice <= products.size()) {
				Product p = products.get(choice - 1);
				cart.add(p);
				System.out.println(p.getTitle() + " has been added to cart");
				caretaker.save(new Memento(cart));
				totalAmount += p.getPrice();
			}
		}
	}
	
	public void removeProduct(Scanner input) {
		int count = 1;
		int choice = 0;
		for(Product p : cart) {
			System.out.println(count++ +". "+p.getTitle());
		}
		while (choice != -1) {
			System.out.println("Input product id to remove from cart (-1 to exit):");
			choice = input.nextInt();
			input.nextLine();
			if (choice > 0 && choice <= cart.size()) {
				Product p = cart.get(choice - 1);
				cart.remove(p);
				System.out.println(p.getTitle() + " has been removed from cart");
				caretaker.save(new Memento(cart));
				totalAmount -= p.getPrice();
			}
		}
	}
	public void undo() {
		Memento undo = caretaker.undo();
		if (undo != null) { // Ensure there is a state to undo to
	        System.out.print("Undoing ");
	        if (cart.size() > undo.getState().size()) {
	        	System.out.println("item addition");
	        } else {
	            System.out.println("item removal");
	        }
	        cart = new ArrayList<>(undo.getState()); // Restore state
	    } else {
	        System.out.println("Nothing to undo");
	    }
	}
	public void redo() {
		Memento redo = caretaker.redo();
		if (redo != null) { // Ensure there is a state to redo to
	        System.out.print("Redoing ");
	        if (cart.size() > redo.getState().size()) {
	            System.out.println("item removal");
	        } else {
	            System.out.println("item addition");
	        }
	        cart = new ArrayList<>(redo.getState()); // Restore state
	    } else {
	        System.out.println("Nothing to redo");
	    }
	}
	public List<Product> getCart(){
		return this.cart;
	}
	public void clearCart() {
		cart.clear();
		caretaker.reset();
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	
	
	

}
