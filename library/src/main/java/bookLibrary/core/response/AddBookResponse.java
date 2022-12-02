package bookLibrary.core.response;

import bookLibrary.core.domain.Book;

import java.util.List;

public class AddBookResponse extends CoreResponse{
    private Book book;

    public AddBookResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddBookResponse(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

}
