package lesson_3_request_response_input_data_validation.code.after.core.services;

import java.util.List;

import lesson_3_request_response_input_data_validation.code.after.core.domain.Book;
import lesson_3_request_response_input_data_validation.code.after.database.Database;

public class GetAllBooksService {

	private Database database;

	public GetAllBooksService(Database database) {
		this.database = database;
	}

	public List<Book> execute() {
		return database.getAllBooks();
	}

}