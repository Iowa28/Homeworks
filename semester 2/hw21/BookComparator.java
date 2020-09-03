package hw21;

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
}
