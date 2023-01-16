package in.nilesh.designpattern.behavioral;

/*
Iterator is a behavioral design pattern that lets you traverse elements of a collection without 
exposing its underlying representation (list, stack, tree, etc.).
*/


interface Iterator<T> {
	public boolean hasNext();

	public T next();
}

interface Collection<T> {
	public Iterator<T> createIterator();

	public void add(T object);
}

class Book {
	private String title;
	private String author;

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String toString() {
		return title + " by - " + author;
	}
}

class MyCollection<T> implements Collection<T> {
	private static int COLLECTION_SIZE = 10;
	@SuppressWarnings("unchecked")
	private T[] books = (T[]) new Object[COLLECTION_SIZE];
	private int index = 0;

	public class MyIterator implements Iterator<T> {

		private int index = 0;

		public boolean hasNext() {
			if (index < COLLECTION_SIZE && books[index] != null) {
				return true;
			}
			return false;
		}

		public T next() {
			if (hasNext()) {
				return books[index++];
			} else {
				return null;
			}
		}
	}

	public Iterator<T> createIterator() {
		return new MyIterator();
	}

	public void add(T object) {
		if (index < COLLECTION_SIZE) {
			books[index++] = object;
		}
	}
}

public class IteratorDesignPattern {
	public static void main(String[] args) {
		MyCollection<Book> bookCollection = new MyCollection<Book>();
		bookCollection.add(new Book("My First Book", "Nilesh"));
		bookCollection.add(new Book("How not to jump from 21st floor", "Deadone"));
		bookCollection.add(new Book("How to be remembered", "Someone"));
		bookCollection.add(new Book("My joyful experience with Windows", "Noone"));

		Iterator<Book> bookIterator = bookCollection.createIterator();
		while (bookIterator.hasNext()) {
			Book book = bookIterator.next();
			System.out.println(book.toString());
		}

	}
}
