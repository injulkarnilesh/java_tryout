package in.nilesh.designpattern.structural;

/*
Adapter is a structural design pattern that allows objects with incompatible interfaces to collaborate.
*/

class LegacyLine {
	public void draw(int x1, int y1, int x2, int y2){
		System.out.println("LEGACY : DRAWING LINE FROM (" + x1 + ", " + y1+") TO (" + x2 + ", " + y2 + ")");
	}
}

class LegacyReactangle {
	public void draw(int topRightX, int topRightY, int height, int width){
		System.out.println("LEGACY: DRAWING LINE FROM (" + topRightX + ", " + topRightY + ") "
				+ "OF WIDTH " + width + " AND HEIGHT " + height);
	}
}

interface Shape {
	public void draw(int x1, int y1, int x2, int y2);
}

class Line implements Shape{
	
	LegacyLine theLine = new LegacyLine();
	
	public void draw(int x1, int y1, int x2, int y2) {
		theLine.draw(x1, y1, x2, y2);
	}
}

class Rectangle implements Shape{
	LegacyReactangle theRectangle = new LegacyReactangle();

	public void draw(int x1, int y1, int x2, int y2) {
		theRectangle.draw(Math.min(x1, x2), Math.min(y1, y2), Math.abs(y1 - y2), Math.abs(x1 - x2));
	}
}

public class Adapter {
	public static void main(String [] argsl) {
		Shape shapes[] = {new Line(), new Rectangle()};
		
		for(Shape shape : shapes){
			int x1 = 10, y1 = 20, x2 = 60, y2 = 50;
			shape.draw(x1, y1, x2, y2);
		}
	}
}
