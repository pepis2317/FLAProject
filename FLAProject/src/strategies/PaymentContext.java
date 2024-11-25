package strategies;

public class PaymentContext {
	private PaymentStrategy paymentStrategy;

    // Set the payment strategy at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Execute the payment
    public void pay(double amount) {
        paymentStrategy.pay(amount);
    }
}
