package teacher.lesson_2_single_responsibility_principle.code.after.database;

import java.util.List;

import teacher.lesson_2_single_responsibility_principle.code.after.Book;

public interface Database {

	void save(Book book);

	void deleteById(Long id);

	List<Book> getAllBooks();

}
