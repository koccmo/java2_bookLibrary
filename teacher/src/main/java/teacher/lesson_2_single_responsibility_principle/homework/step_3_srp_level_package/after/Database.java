package teacher.lesson_2_single_responsibility_principle.homework.step_3_srp_level_package.after;

import java.util.List;

public interface Database {

	void save(Book book);

	void deleteById(Long id);

	List<Book> getAllBooks();

}
