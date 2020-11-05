package teacher.applications.book_library.services;

import java.util.List;

import teacher.applications.book_library.Book;
import teacher.applications.book_library.database.Database;

public class GetAllBooksService {

	private final Database database;

	public GetAllBooksService(Database database) {
		this.database = database;
	}

	public List<Book> getAllBooks() {
		return database.getAllBooks();
	}

}
