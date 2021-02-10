package lesson_12.core.responses.book;

import lesson_12.core.domain.Book;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

import java.util.List;

public class AddBookResponse extends CoreResponse {

    private Book newBook;

    public AddBookResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddBookResponse(Book newBook) {
        this.newBook = newBook;
    }

    public Book getNewBook() {
        return newBook;
    }

}
