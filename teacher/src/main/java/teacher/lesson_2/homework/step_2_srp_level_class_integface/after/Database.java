package teacher.lesson_2.homework.step_2_srp_level_class_integface.after;

import java.util.List;

public interface Database {

	void save(Book book);

	void deleteById(Long id);

	List<Book> getAllBooks();

}
