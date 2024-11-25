package decorators;

import strategies.PaymentStrategy;

public class FastShippingDecorator implements PaymentStrategy {
	private double additionalChargePercentage = 1.0; // Increase amount by 1%
    private PaymentStrategy paymentStrategy;
    private double originalAmount;
    
    public FastShippingDecorator(PaymentStrategy paymentStrategy, double originalAmount) {
        this.paymentStrategy = paymentStrategy; // Use the parent decorator field
        this.originalAmount = originalAmount;
    }

    @Override
    public void pay(double amount) {
        double newAmount = amount + (originalAmount * additionalChargePercentage / 100);
        System.out.println("Product is sent via FastShipping. New amount is "+newAmount);
        paymentStrategy.pay(newAmount);
    }
}
