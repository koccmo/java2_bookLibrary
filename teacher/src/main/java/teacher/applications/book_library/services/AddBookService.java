package teacher.applications.book_library.services;

import teacher.applications.book_library.Book;
import teacher.applications.book_library.database.Database;

public class AddBookService {

	private final Database database;

	public AddBookService(Database database) {
		this.database = database;
	}

	public void addBook(String bookTitle, String bookAuthor) {
		Book book = new Book(bookTitle, bookAuthor);
		database.add(book);
	}

}
