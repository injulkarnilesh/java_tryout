package in.nilesh.designpattern.behavioral;

/*
Template Method is a behavioral design pattern that defines the skeleton of an algorithm in the superclass 
but lets subclasses override specific steps of the algorithm without changing its structure.
*/

abstract class House{
	public void buildHouse(){
		layFaoundation();
		raisePillars();
		buildWalls();
		placeDoorsAndWindows();
		buildRoof();
		System.out.println("House done!");
	}
	
	public abstract void layFaoundation();
	public abstract void raisePillars();
	public abstract void buildWalls();
	public abstract void placeDoorsAndWindows();
	public abstract void buildRoof();
}

class ConcreteHouse extends House {

	@Override
	public void layFaoundation() {
		System.out.println("Lay down concrete foundation");
		
	}

	@Override
	public void raisePillars() {
		System.out.println("Raising Cement Concrete pillars");
	}

	@Override
	public void buildWalls() {
		System.out.println("Biuld walls of bricks and cement");
	}

	@Override
	public void placeDoorsAndWindows() {
		System.out.println("Wooden doors and glass windows");
	}

	@Override
	public void buildRoof() {
		System.out.println("Build RCC slab");
	}
	
}

class SimpleHouse extends House {

	@Override
	public void layFaoundation() {
		System.out.println("Foundation using stones");
		
	}

	@Override
	public void raisePillars() {
		System.out.println("Raising pillars of bricks");
	}

	@Override
	public void buildWalls() {
		System.out.println("Biuld walls of bricks and clay");
	}

	@Override
	public void placeDoorsAndWindows() {
		System.out.println("Wooden doors and windows");
	}

	@Override
	public void buildRoof() {
		System.out.println("Lay down roof tiles");
	}
	
}

public class Template {
	public static void main(String[] args) {
		System.out.println("************************");
		House rccHouse = new ConcreteHouse();
		rccHouse.buildHouse();
		
		System.out.println("************************");
		House simpleHouse = new SimpleHouse();
		simpleHouse.buildHouse();
	}
}
