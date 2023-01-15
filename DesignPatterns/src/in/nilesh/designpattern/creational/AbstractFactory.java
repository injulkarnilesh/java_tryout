package in.nilesh.designpattern.creational;

/*
Factory of Factory.
OR
Abstract Factory is a creational design pattern that 
lets you produce families of related objects without specifying their concrete classes.
*/


abstract class GUIFacatory{
	public abstract Button createButton();
	public abstract DropDown createDropDown();
}


/**
 *Dark and Light are family types, so the factory types
 */
class DarkThemeGUIFactory extends GUIFacatory {
	static private DarkThemeGUIFactory instance = new DarkThemeGUIFactory();
	
	private DarkThemeGUIFactory(){}
	
	static public DarkThemeGUIFactory getInstance(){
		return instance;
	}
	
	public Button createButton() {
		return new DarkButton();
	}

	public DropDown createDropDown() {
		return new DarkDropDown();
	}
}

class LightThemeGUIFactory extends GUIFacatory {
	
	static private LightThemeGUIFactory instance = new LightThemeGUIFactory();
	
	private LightThemeGUIFactory(){}
	
	static public LightThemeGUIFactory getInstance(){
		return instance;
	}
	
	public Button createButton() {
		return new LightButton();
	}

	public DropDown createDropDown() {
		return new LightDropDown();
	}
}
/**
 *Button and DropDown are product types, so the abstract products
 */
abstract class Button{
	public abstract void drawButton();
}
class DarkButton extends Button {
	public void drawButton() {
		System.out.println("Drawing Dark Button");
	}
}
class LightButton extends Button {
	public void drawButton() {
		System.out.println("Drawing Light Button");
	}
}

abstract class DropDown{
	public abstract void drawDropDown();
}
class DarkDropDown extends DropDown {
	public void drawDropDown() {
		System.out.println("Drawing dark dropdown");
	}
}
class LightDropDown extends DropDown {
	public void drawDropDown() {
		System.out.println("Drawing light dropdown");
	}
}


public class AbstractFactory {
 
	public static void main(String[] args) {
		System.out.println("######DARK THEME######");
		GUIFacatory dGuiFactory = DarkThemeGUIFactory.getInstance();
		Button dButton = dGuiFactory.createButton();
		dButton.drawButton();
		DropDown dDropDown = dGuiFactory.createDropDown();
		dDropDown.drawDropDown();
		
		System.out.println("\n######LIGHT THEME######");
		GUIFacatory lGuiFactory = LightThemeGUIFactory.getInstance();
		Button lButton = lGuiFactory.createButton();
		lButton.drawButton();
		DropDown lDropDown = lGuiFactory.createDropDown();
		lDropDown.drawDropDown();
		
	}
}
