package teacher.lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.before;

import java.util.List;

public interface Database {

	void save(Book book);

	void deleteById(Long id);

	List<Book> getAllBooks();

}
