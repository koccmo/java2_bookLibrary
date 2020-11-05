package teacher.applications.book_library.services;

import teacher.applications.book_library.Book;
import teacher.applications.book_library.database.Database;

public class DeleteBookService {

	private final Database database;

	public DeleteBookService(Database database) {
		this.database = database;
	}

	public void deleteBook(String bookTitle, String bookAuthor) {
		database.remove(new Book(bookTitle, bookAuthor));
	}

}
