package hw21;

import static org.junit.Assert.*;

import org.junit.Test;

public class BookTest {

	@Test
	public void compareTest() {
		Book book1 = new Book("Katie Sierra", "Head First Java");
		Book book2 = new Book("Katie Sierra", "Head First Java");
		assertEquals(0, new BookComparator().compare(book1, book2));
	}
	
	@Test
	public void sortTest() {
		Book book1 = new Book("Lev Tolstoy", "War and Peace");
		Book book2 = new Book("Lev Tolstoy", "Anna Karenina");
		Book book3 = new Book("Michael Bulgakov", "Master and Margarite");
		Book book4 = new Book("Karl Marx", "Capital");
		Book[] books = new Book[]{book1, book2, book3, book4};
		Book[] sortedBooks = new Book[] {book4, book2, book1, book3};
		
		new BookComparator().sort(books);
		assertEquals(sortedBooks[0], books[0]);
	}
}
