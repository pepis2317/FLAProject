package decorators;

import strategies.PaymentStrategy;

public class GiftwrapDecorator implements PaymentStrategy {
    private String color;// Increase amount by 1%
    private PaymentStrategy paymentStrategy;
    public GiftwrapDecorator(PaymentStrategy paymentStrategy, String color) {
        this.paymentStrategy = paymentStrategy; // Use the parent decorator field
        this.color = color;
    }

    @Override
    public void pay(double amount) {
        double newAmount = amount + 2;
        System.out.println("Product wrapped in " + color + " wrapping. New amount is "+newAmount);
        paymentStrategy.pay(newAmount);
    }
}