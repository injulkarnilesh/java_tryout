package in.nilesh.designpattern.creational;

/*
 * Product
 */
class Pizza {
	private String doug;
	private String topping;
	private String sause;
	
	public String getDoug() {
		return doug;
	}
	public void setDoug(String doug) {
		this.doug = doug;
	}
	public String getTopping() {
		return topping;
	}
	public void setTopping(String topping) {
		this.topping = topping;
	}
	public String getSause() {
		return sause;
	}
	public void setSause(String sause) {
		this.sause = sause;
	}
	
	public String toString(){
		return "Pizza with " + doug + " " + topping + " " + sause;
	}
} 

/*
 * Abstract Builder
 */
abstract class PizzaBuilder {
	protected Pizza pizza;
	
	public Pizza getPizza(){
		return pizza;
	}
	
	public void createNewPizza(){
		pizza = new Pizza();
	}
	
	public abstract void buildDoug();
	public abstract void buildTopping();
	public abstract void buildSause();
}

/*
 *Concrete Builder 
 */
class MexicanPizzaBuilder extends PizzaBuilder{

	public void buildDoug() {
		pizza.setDoug("MEXICAN DOUG");
	}

	public void buildTopping() {
		pizza.setTopping("MEXICAN TOPPING");
	}

	public void buildSause() {
		pizza.setSause("MEXICAN SAUSE");
	}
	
}

/*
 *Concrete Builder 
 */
class ItalianPizzaBuilder extends PizzaBuilder{

	public void buildDoug() {
		pizza.setDoug("ITALIAN DOUG");
	}

	public void buildTopping() {
		pizza.setTopping("ITALIAN TOPPING");
	}

	public void buildSause() {
		pizza.setSause("ITALIAN SAUSE");
	}
	
}

/*
 * Director
 */
class Waiter {
	private PizzaBuilder pizzaBuilder;
	
	public void setPizzaBuilder(PizzaBuilder pBuilder){
		pizzaBuilder = pBuilder;
	}
	
	public void createPizza(){
		pizzaBuilder.createNewPizza();
		pizzaBuilder.buildDoug();
		pizzaBuilder.buildTopping();
		pizzaBuilder.buildSause();
	}
	
	public Pizza getPizza(){
		return pizzaBuilder.getPizza();
	}
}

/*
 * Client
 */
public class Builder {
	public static void main(String[] args) {
		Waiter waiter = new Waiter();
		PizzaBuilder italianPizzaBuilder = new ItalianPizzaBuilder();
		waiter.setPizzaBuilder(italianPizzaBuilder);
		waiter.createPizza();
		Pizza italianPizza = waiter.getPizza();
		System.out.println("CREATED \n" + italianPizza);
		
		PizzaBuilder mexicanPizzaBuilder = new MexicanPizzaBuilder();
		waiter.setPizzaBuilder(mexicanPizzaBuilder);
		waiter.createPizza();
		Pizza mexicanPizza = waiter.getPizza();
		System.out.println("CREATED \n" + mexicanPizza);
	}
}
