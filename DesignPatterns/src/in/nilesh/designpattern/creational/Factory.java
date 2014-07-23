package in.nilesh.designpattern.creational;

import in.nilesh.designpattern.exception.WrongProductIDException;

interface Product{
	void printProductType();
}

class ProductOne implements Product{

	public void printProductType() {
		System.out.println("The Product One!");
	}
}

class ProductTwo implements Product{

	public void printProductType() {
		System.out.println("The Product Two!");
	}
}

class ProductFactory {
	public static Product createProduct(int i) throws WrongProductIDException{
		Product product = null;
		if(i == 1){
			product = new ProductOne();
		} else if(i==2){
			product = new ProductTwo();
		} 
		else {
			throw new WrongProductIDException("Product ID " + i +" does not match any product.");
		}
		return product;
	}
}


public class Factory {
	public static void main(String[] args) {
		Product p1, p2, p3;
		try {
			p1 = ProductFactory.createProduct(1);
			p1.printProductType();
			
			p2 = ProductFactory.createProduct(2);
			p2.printProductType();
			
			p3 = ProductFactory.createProduct(3);
			p3.printProductType();

		} catch (WrongProductIDException e) {
			e.printStackTrace();
		}
	}
}
