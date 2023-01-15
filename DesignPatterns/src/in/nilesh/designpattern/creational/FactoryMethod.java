package in.nilesh.designpattern.creational;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
Factory Method is a creational design pattern that provides an interface for creating objects in a superclass, 
but allows subclasses to alter the type of objects that will be created.
*/

abstract class Document{
	protected String name = "defaultname";
	
	public void saveAs(String name){
		this.name = name;
		this.save();
	}
	
	public abstract void save();
	public abstract void open();
	public abstract void close();
	
	public String toString(){
		return "Document with name " + name;
	}
}

class DrwaingDocument extends Document {
	
	public DrwaingDocument(){}
	
	public DrwaingDocument(String name){
		this.name = name;
	}
	
	public void save(){
		System.out.println("Saving drawing doc.");
	}
	
	public void open(){
		System.out.println("Opening drawing doc.");
	}
	
	public void close() {
		System.out.println("Closing drawing doc.");
	}
	
	public String toString(){
		return "Drawing " + super.toString();
	}
}

class TextDocument extends Document {
	
	public TextDocument() {	}
	
	public TextDocument(String name) {
		this.name = name;
	}
	
	public void save(){
		System.out.println("Saving text doc.");
	}
	
	public void open(){
		System.out.println("Opening text doc.");
	}
	
	public void close() {
		System.out.println("Closing text doc.");
	}
	
	public String toString(){
		return "Text " + super.toString();
	}

}

abstract class Application {
	private List<Document> openDocuments = new ArrayList<Document>();
	
	protected abstract Document createDocument();
		
	public Document newDocument(){
		Document document = createDocument();
		addOpenDocument(document);
		document.open();
		return document;
	}
	
	public void addOpenDocument(Document document){
		openDocuments.add(document);
	}
	
	public void closeAndRemoveAllOpenDocument() {
		Iterator<Document> docIterator = openDocuments.iterator();
		while (docIterator.hasNext()) {
			Document doc = docIterator.next();
			doc.close();
			docIterator.remove();
		}
	}
	
	public void listOpenDocuments(){
		System.out.println("***********OPEN DOCUMENTS********");
		for(Document doc : openDocuments){
			System.out.println(doc);
		}
	}
}


class DrawingApplication extends Application{

	public Document createDocument() {
		return new DrwaingDocument();
	}
}

class TextApplication extends Application{

	public Document createDocument() {
		return new TextDocument();
	}
}

public class FactoryMethod {

	public static void main(String[] args) {
		Application textApp = new TextApplication();
		Document textDoc1 = textApp.newDocument();
		textDoc1.saveAs("Nilesh.txt");
		Document textDoc2 = textApp.newDocument();
		textDoc2.saveAs("Injulkar.txt");

		textApp.listOpenDocuments();
		textApp.closeAndRemoveAllOpenDocument();
		
		Application drawingApp = new DrawingApplication();
		Document drawingDoc1 = drawingApp.newDocument();
		drawingDoc1.save();
		drawingApp.listOpenDocuments();
		
	}
}
