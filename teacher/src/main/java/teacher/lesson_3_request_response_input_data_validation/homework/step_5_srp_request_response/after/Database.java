package teacher.lesson_3_request_response_input_data_validation.homework.step_5_srp_request_response.after;

import java.util.List;

public interface Database {

	void save(Book book);

	boolean deleteById(Long id);

	List<Book> getAllBooks();

}
