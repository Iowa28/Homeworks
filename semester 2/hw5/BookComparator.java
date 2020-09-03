package inf5;

import java.util.Comparator;

public class BookComparator implements Comparator<Book>{
	@Override
	public int compare(Book o1, Book o2) {
		int result = o1.getAuthor().compareTo(o2.getAuthor());
		if (result == 0) {
			result = o1.getTitle().compareTo(o2.getTitle());
		}
		return result;
	}
	
	public void sort(Book[] books) {
		for (int j = 0; j < books.length; j++) {
			for (int i = 0; i < books.length - 1; i++) {
				if (new BookComparator().compare(books[i], books[i + 1]) > 0) {
					Book s = books[i];
					books[i] = books[i + 1];
					books[i + 1] = s;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Book book1 = new Book("Lev Tolstoy", "War and Peace");
		Book book2 = new Book("Lev Tolstoy", "Anna Karenina");
		Book book3 = new Book("Michael Bulgakov", "Master and Margarite");
		Book book4 = new Book("Karl Marx", "Capital");
		
		Book[] books = new Book[]{book1, book2, book3, book4};
		
		for(Book book: books) {
			System.out.println(book.getAuthor() + ": " + book.getTitle());
		}
		System.out.println();
		
		new BookComparator().sort(books);
		for(Book book: books) {
			System.out.println(book.getAuthor() + ": " + book.getTitle());
		}
	}
}
