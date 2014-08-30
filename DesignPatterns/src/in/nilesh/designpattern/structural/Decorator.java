package in.nilesh.designpattern.structural;

import java.util.Calendar;

interface Widget{
	public void draw();	
}

class TimeWidget implements Widget{
	
	@Override
	public void draw() {
		System.out.print(Calendar.getInstance().getTime());
	}
}

abstract class WidgetDecorator implements Widget{
	private Widget widget;
	
	public WidgetDecorator(Widget widget) {
		this.widget = widget;
	}
	
	public void draw(){
		widget.draw();
	};
}

class BorderWidgetDecorator extends WidgetDecorator {
	
	public BorderWidgetDecorator(Widget widget) {
		super(widget);
	}
	
	@Override
	public void draw() {
		System.out.println("-----------------------------------");
		super.draw();
		System.out.println("\n-----------------------------------");
		
	}
}

class VerticalSrollBarWidgetDecorator extends WidgetDecorator{
	
	public VerticalSrollBarWidgetDecorator(Widget widget) {
		super(widget);
	}	
	
	@Override
	public void draw() {
		super.draw();
		System.out.print("    |\n");
	}
}

class HorizontalSrollBarWidgetDecorator extends WidgetDecorator{
	
	public HorizontalSrollBarWidgetDecorator(Widget widget) {
		super(widget);
	}	
	
	@Override
	public void draw() {
		super.draw();
		System.out.println("\n________________________________");
	}
}

public class Decorator {
	public static void main(String[] args) {
		System.out.println("\nPLAIN WIDGET\n");
		Widget simpleTime = new TimeWidget();
		simpleTime.draw();
		
		System.out.println("\n\nDECORATED WIDGET\n");
		Widget decoratedWidget = new BorderWidgetDecorator(
				new HorizontalSrollBarWidgetDecorator(
						new VerticalSrollBarWidgetDecorator(
								new TimeWidget())));
		decoratedWidget.draw();
		
		System.out.println("\n WIDGET WITH BORDER\n");		
		Widget borderedWidget = new BorderWidgetDecorator( new TimeWidget());						
		borderedWidget.draw();
		
		System.out.println("\n WIDGET WITH SCROLEBARS\n");
		Widget scrollableWidget = new HorizontalSrollBarWidgetDecorator(
				new VerticalSrollBarWidgetDecorator(
						new TimeWidget()));
		scrollableWidget.draw();
		
	}
}
