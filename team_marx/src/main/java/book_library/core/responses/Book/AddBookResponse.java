package book_library.core.responses.Book;

import book_library.core.domain.Book;
import book_library.core.responses.CoreError;
import book_library.core.responses.CoreResponse;

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
