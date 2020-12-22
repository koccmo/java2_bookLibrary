package book_library.core.responses;

import book_library.Book;

import java.util.List;

public class AddBookResponse extends CoreResponse{

    private Book newBook;

    public AddBookResponse(Book newBook) {
        this.newBook = newBook;
    }

    public AddBookResponse (List<CoreError> errors) {
        super(errors);
    }

    public Book getNewBook() {
        return newBook;
    }
}
