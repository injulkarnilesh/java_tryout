package in.nilesh.designpattern.structural;

import java.util.ArrayList;
import java.util.List;

/*
Composite is a structural design pattern 
that lets you compose objects into tree structures and then work with these structures as if they were individual objects.

Using the Composite pattern makes sense only when the core model of your app can be represented as a tree.
*/

abstract class AbstractFile {
	protected String name;
	protected static StringBuilder indentString = new StringBuilder();
	
	protected static String FILE_NAME_PREFIXER = "|-- ";
	protected static String DIRECTORY_INDENT = "|   ";
	
	public abstract void tree(); 
	
	public void resetIndent(){
		indentString = new StringBuilder();
	}
	
	protected void addIntend(){
		indentString.append(DIRECTORY_INDENT);
	} 
	
	protected void removeIntend(){
		indentString.replace(0, indentString.length(), 
				indentString.substring(0, indentString.length() - DIRECTORY_INDENT.length()));
	} 
	
	protected void printIndentedFileName(){
		System.out.println(indentString + FILE_NAME_PREFIXER + this.name);
	}
}

class File extends AbstractFile {

	public File(String name){
		this.name = name;
	}
	
	@Override
	public void tree() {
		printIndentedFileName();
	}
}

class Directory extends AbstractFile{
	
	private List<AbstractFile> containedFiles = new ArrayList<AbstractFile>();
	
	public Directory(String name){
		this.name = name;
	}
	
	public void addFile(AbstractFile file){
		containedFiles.add(file);
	}
	
	@Override
	public void tree() {
		printIndentedFileName();
		addIntend();
		for(AbstractFile aFile : containedFiles){
			aFile.tree();			
		}
		removeIntend();
	}
		
}

public class Composite {
	
	public static void main(String[] args) {
		Directory homeDirectory = new Directory("Home");
		
		Directory downloadsDirectory = new Directory("Downloads");
		downloadsDirectory.addFile(new File("Eva_Green_Wallpaper.jpg"));
		downloadsDirectory.addFile(new File("Mauli_Maluli.mp3"));
		Directory gardiansDirectory = new Directory("Gardians of the galaxy 2014");
		gardiansDirectory.addFile(new File("Gandians of the galaxy.avi"));
		gardiansDirectory.addFile(new File("Gandians of the galaxy.srt"));
		gardiansDirectory.addFile(new File("Kickass torrents.txt"));
		downloadsDirectory.addFile(gardiansDirectory);
		
		Directory documentsDirectory = new Directory("Documents");
		Directory books = new Directory("books");
		books.addFile(new File("how to win.pdf"));
		books.addFile(new File("Java8.pdf"));
		documentsDirectory.addFile(books);
		documentsDirectory.addFile(new File("testwithme.doc"));
		
		Directory desktopDirectory = new Directory("Desktop");
		desktopDirectory.addFile(new File("ToDo"));
		
		homeDirectory.addFile(new File("Eclipse"));
		homeDirectory.addFile(new File(".bashrc~"));
		homeDirectory.addFile(downloadsDirectory);
		homeDirectory.addFile(documentsDirectory);
		homeDirectory.addFile(desktopDirectory);
		
		homeDirectory.tree();		
	}
}
