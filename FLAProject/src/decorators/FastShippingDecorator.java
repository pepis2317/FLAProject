package decorators;

import strategies.PaymentStrategy;

public class FastShippingDecorator implements PaymentStrategy {
	private double additionalChargePercentage = 1.0; // Increase amount by 1%
    private PaymentStrategy paymentStrategy;
    
    public FastShippingDecorator(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy; // Use the parent decorator field
    }

    @Override
    public void pay(double amount) {
        double newAmount = amount + (amount * additionalChargePercentage / 100);
        System.out.println("Product is sent via FastShipping. New amount is "+newAmount);
        paymentStrategy.pay(newAmount);
    }

}
