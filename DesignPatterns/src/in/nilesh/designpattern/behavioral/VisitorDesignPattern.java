package in.nilesh.designpattern.behavioral;

/*
Visitor is a behavioral design pattern that lets you separate algorithms from the objects on which they operate.

The Visitor pattern uses a technique called "Double Dispatch", 
which helps to execute the proper method on an object without cumbersome conditionals. 
Instead of letting the client select a proper version of the method to call, 
how about we delegate this choice to objects we’re passing to the visitor as an argument? 
Since the objects know their own classes, they’ll be able to pick a proper method on the visitor less awkwardly. 
They “accept” a visitor and tell it what visiting method should be executed.
*/

/*
 * Visitor Interface
 */
interface OfferVisitor {
	public double visit(Apparel apparel);

	public double visit(ElectronicDevice electronics);
}

/*
 * Visitable iterface
 */
interface ItemElement {
	public double accept(OfferVisitor visitor);
}

/*
 * Concrete Visitable
 */
class Apparel implements ItemElement {

	private double price;
	private String name;
	private int size;

	public Apparel(double price, String name, int size) {
		this.price = price;
		this.name = name;
		this.size = size;
	}

	public double accept(OfferVisitor visitor) {
		return visitor.visit(this);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}

/*
 * Concrete Visitable
 */
class ElectronicDevice implements ItemElement {
	private double price;
	private String name;

	public ElectronicDevice(double price, String name) {
		this.price = price;
		this.name = name;
	}

	@Override
	public double accept(OfferVisitor visitor) {
		return visitor.visit(this);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

/*
 * Concrete Visitor
 */
class MansoonSale implements OfferVisitor {

	public double visit(Apparel apparel) {
		double offerAmount = 0;
		double apparelPrice = apparel.getPrice();

		if ((apparelPrice >= 1000) && (apparelPrice <= 3000)) {
			offerAmount = 100;
		} else if (apparelPrice > 3000) {
			offerAmount = 500;
		}

		System.out.println("You will have an off of " + offerAmount + " on "
				+ apparel.getName() + " of " + apparel.getPrice());
		return offerAmount;
	}

	public double visit(ElectronicDevice electronics) {
		double offerAmount = 0;
		double apparelPrice = electronics.getPrice();

		if ((apparelPrice >= 20000) && (apparelPrice <= 50000)) {
			offerAmount = 2000;
		} else if (apparelPrice > 50000) {
			offerAmount = 5000;
		}

		System.out.println("You will have an off of " + offerAmount + " on "
				+ electronics.getName() + " of " + electronics.getPrice());
		return offerAmount;
	}

}

/*
 * Concrete Visitor
 */
class DiwaliSale implements OfferVisitor {

	public double visit(Apparel apparel) {
		double offerAmount = apparel.getPrice() * (10D / 100D);

		System.out.println("You will have an off of " + offerAmount + " on "
				+ apparel.getName() + " of " + apparel.getPrice());

		return offerAmount;
	}

	public double visit(ElectronicDevice electronics) {
		double offerAmount = electronics.getPrice() * (25D / 100D);

		System.out.println("You will have an off of " + offerAmount + " on "
				+ electronics.getName() + " of " + electronics.getPrice());

		return offerAmount;
	}
}

public class VisitorDesignPattern {
	public static void main(String[] args) {
		OfferVisitor mansoonOff = new MansoonSale();
		OfferVisitor diwaliOff = new DiwaliSale();

		ItemElement items[] = {
				new Apparel(799, "Red TShirt", 40),
				new Apparel(1789.00, "Yellow pant", 36),
				new ElectronicDevice(35000.00, "Color TV"),
				new ElectronicDevice(89709.00,
						"White Laptop with a fruit on back") };

		System.out.println("********HAPPY MANSOON************");
		for (ItemElement item : items) {
			item.accept(mansoonOff);
		}

		System.out.println("\n********HAPPY DIWALI************");
		for (ItemElement item : items) {
			item.accept(diwaliOff);
		}

	}
}
