package mementos;

import java.util.ArrayList;
import java.util.List;

import models.Product;

public class Memento {
	private final List<Product> state;
	public Memento(List<Product> state) {
		this.state = new ArrayList<>(state);
	}
	public List<Product> getState(){
		return new ArrayList<>(state);
	}
}
