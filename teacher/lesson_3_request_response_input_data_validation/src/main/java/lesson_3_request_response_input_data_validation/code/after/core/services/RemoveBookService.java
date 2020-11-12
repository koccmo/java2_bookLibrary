package lesson_3_request_response_input_data_validation.code.after.core.services;

import lesson_3_request_response_input_data_validation.code.after.database.Database;

public class RemoveBookService {

	private Database database;

	public RemoveBookService(Database database) {
		this.database = database;
	}

	public void execute(Long bookId) {
		database.deleteById(bookId);
	}

}