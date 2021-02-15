package lv.javaguru.java2.library.core.responses;

import java.util.List;

import lv.javaguru.java2.library.core.domain.Book;

public class DeleteBookResponse extends CoreResponse {

	private Book book;

	public DeleteBookResponse(List<CoreError> errors) {
		super(errors);
	}

	public DeleteBookResponse(Book book) {
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

}
