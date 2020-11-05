package teacher.applications.book_library.database;

import java.util.List;

import teacher.applications.book_library.Book;

public interface Database {

	void add(Book book);

	void remove(Book book);

	List<Book> getAllBooks();

}
