package teacher.applications.book_library.database;

import java.util.ArrayList;
import java.util.List;

import teacher.applications.book_library.Book;

public class InMemoryDatabase implements Database {

	private Long idCounter = 1L;
	private List<Book> books = new ArrayList<>();

	@Override
	public void add(Book book) {
		book.setId(idCounter);
		idCounter++;
		books.add(book);
	}

	@Override
	public void remove(Book book) {
		books.remove(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return books;
	}
}
