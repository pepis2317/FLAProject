package decorators;

import strategies.PaymentStrategy;

public class DiscountDecorator implements PaymentStrategy {
    private double discountPercentage;
    private PaymentStrategy decoratedPS;
    public DiscountDecorator(PaymentStrategy paymentStrategy, double discountPercentage) {
        this.decoratedPS = paymentStrategy;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public void pay(double amount) {
        double discountedAmount = amount - (amount * discountPercentage / 100);
        System.out.println("DISCOUNT: Applied " + discountPercentage + "% discount. Final amount: " + discountedAmount);
        decoratedPS.pay(discountedAmount);
    }
}