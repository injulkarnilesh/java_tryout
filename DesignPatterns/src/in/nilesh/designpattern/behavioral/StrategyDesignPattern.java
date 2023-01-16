package in.nilesh.designpattern.behavioral;

import java.util.ArrayList;
import java.util.List;

/*
Strategy is a behavioral design pattern that lets you define a family of algorithms, 
put each of them into a separate class, and make their objects interchangeable.
*/


interface PaymentStrategy {
	public boolean pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {

	private String cardNumber;
	private String nameOnCard;
	private String cvv;
	private String expiryDate;

	public CreditCardPayment(String cardNumber, String nameOnCard, String cvv,
			String expiryDate) {
		this.cardNumber = cardNumber;
		this.nameOnCard = nameOnCard;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
	}

	@Override
	public boolean pay(double amount) {
		System.out
				.println("Making payment of " + amount + " using Credit Card");
		return true;
	}
}

class PaypalPayment implements PaymentStrategy {
	private String emailAddress;
	private String password;

	public PaypalPayment(String emailAddress, String password) {
		this.emailAddress = emailAddress;
		this.password = password;
	}

	@Override
	public boolean pay(double amount) {
		System.out.println("Making payment of " + amount + " using Paypal");
		return true;
	}
}

class Item {
	private String itemId;
	private double price;

	public Item(String itemId, double price) {
		this.itemId = itemId;
		this.price = price;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}

class ShoppingCart {
	private List<Item> itemsAdded = new ArrayList<>();
	private PaymentStrategy paymentStrategy;

	public void addItem(Item item) {
		itemsAdded.add(item);
	}

	private double calculatTotal() {
		double total = 0;
		for (Item item : itemsAdded) {
			total += item.getPrice();
		}
		return total;
	}

	public void setPaymentStratefy(PaymentStrategy paymentStrategy) {
		this.paymentStrategy = paymentStrategy;
	}

	public void makePayment() {
		paymentStrategy.pay(calculatTotal());
	}

	public void clearCart() {
		itemsAdded = new ArrayList<>();
	}

}

public class StrategyDesignPattern {
	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();

		Item item1 = new Item("AE2323", 343.78);
		Item item2 = new Item("NJ5544", 5233.00);
		Item item3 = new Item("UV5666", 145.38);
		cart.addItem(item1);
		cart.addItem(item2);
		cart.addItem(item3);

		PaymentStrategy payStrategy =
				new PaypalPayment("thenameisbondjamesbond@gmail.com", "12345");
		cart.setPaymentStratefy(payStrategy);
		cart.makePayment();

		cart.clearCart();

		Item item4 = new Item("GH5555", 45.08);
		cart.addItem(item4);
		PaymentStrategy anotherPayStrategy = 
				new CreditCardPayment("3234 4623", "JAMES BOND", "007", "06/05/2045");
		cart.setPaymentStratefy(anotherPayStrategy);
		cart.makePayment();
	}
}
