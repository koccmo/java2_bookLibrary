package lesson_3_request_response_input_data_validation.code.after.core.requests;

public class AddBookRequest {

	private String bookTitle;
	private String bookAuthor;

	public AddBookRequest(String bookTitle, String bookAuthor) {
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}
}
