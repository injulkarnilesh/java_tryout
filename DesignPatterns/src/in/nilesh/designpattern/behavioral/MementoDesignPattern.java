package in.nilesh.designpattern.behavioral;

import java.util.Stack;

interface PreviousCalculationForCaluclator {
	public int getFirstNumber();

	public int getSecondNumber();
}

/*
 * Memento to store the state
 */
class Memento implements PreviousCalculationForCaluclator {

	private int firstNumber, secondNumber;

	public Memento(int firstNo, int secondNo) {
		this.firstNumber = firstNo;
		this.secondNumber = secondNo;
	}

	public int getFirstNumber() {
		return firstNumber;
	}

	public int getSecondNumber() {
		return secondNumber;
	}
}

/*
 * Originator
 */
class Calculator {
	private int firstNumber;
	private int secondNumber;

	public Calculator(int firstNo, int secondNo) {
		this.firstNumber = firstNo;
		this.secondNumber = secondNo;
	}

	public void setFirstNumber(int firstNo) {
		this.firstNumber = firstNo;
	}

	public void setSecondNumber(int secondNo) {
		this.secondNumber = secondNo;
	}

	public int calculateResult() {
		return firstNumber + secondNumber;
	}

	public Memento saveToMemmeto() {
		return new Memento(firstNumber, secondNumber);
	}

	public void restorePreviousMemento(Memento memento) {
		setFirstNumber(memento.getFirstNumber());
		setSecondNumber(memento.getSecondNumber());
	}
}

/*
 * Caretaker
 */
class CareTaker {
	private Stack<Memento> mementos = new Stack<>();

	public void addMemento(Memento memento) {
		mementos.push(memento);
	}

	public Memento getLastMemento() {
		return mementos.pop();
	}
}

public class MementoDesignPattern {
	public static void main(String[] args) {
		Calculator calc = new Calculator(12, 3);
		System.out.println(calc.calculateResult());

		CareTaker careTaker = new CareTaker();
		careTaker.addMemento(calc.saveToMemmeto());
		calc.setFirstNumber(45);
		calc.setSecondNumber(30);
		System.out.println(calc.calculateResult());
		careTaker.addMemento(calc.saveToMemmeto());
		
		calc.setFirstNumber(32);
		calc.setSecondNumber(32);
		System.out.println(calc.calculateResult());

		calc.restorePreviousMemento(careTaker.getLastMemento());
		System.out.println(calc.calculateResult());
		
		calc.restorePreviousMemento(careTaker.getLastMemento());
		System.out.println(calc.calculateResult());
	}
}
