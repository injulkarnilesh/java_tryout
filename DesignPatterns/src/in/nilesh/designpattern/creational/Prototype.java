package in.nilesh.designpattern.creational;

import java.lang.Cloneable;
import java.util.HashMap;
import java.util.Map;

/*
Prototype is a creational design pattern that lets you copy existing objects without making your code dependent on their classes.
*/

interface Animal extends Cloneable{
	public Animal cloan();
}

class Dog implements Animal {
		
	public Dog() {
		System.out.println("DOG CREATED");
	}
	
	 public Animal cloan(){
		return new Dog();
	}
	 
	public String toString() {
		return "THE DOG";
	}
}

class Cat implements Animal{

	public Cat(){
		System.out.println("CAT CREATED");
	}
	
	public Animal cloan() {
		return new Cat();
	}
	
	public String toString(){
		return "THE CAT";
	}
}

class AnimalFactory {
	private static Map<String, Animal> animalPrototypes = new HashMap<>();
	static {
		animalPrototypes.put("DOG", new Dog());
		animalPrototypes.put("CAT", new Cat());
	}
	
	public static Animal getAnimal(String type){
		return animalPrototypes.get(type).cloan();
	}
}

public class Prototype {
	
	public static void main(String[] args) {
		Animal animal1 = AnimalFactory.getAnimal("DOG");
		System.out.println(animal1);
		
		Animal animal2 = AnimalFactory.getAnimal("CAT");
		System.out.println(animal2);
	}
}
