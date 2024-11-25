package strategies;

public class CreditCardPayment implements PaymentStrategy{
	private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }
	@Override
	public void pay(double amount) {
		// TODO Auto-generated method stub
		System.out.println("Paid " + amount + " using Credit Card. Cardholder: " + cardHolderName + "Card number: "+cardNumber);
	}

}
