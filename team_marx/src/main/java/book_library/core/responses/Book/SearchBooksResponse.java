package book_library.core.responses.Book;

import book_library.core.domain.Book;
import book_library.core.responses.CoreError;
import book_library.core.responses.CoreResponse;

import java.util.List;

public class SearchBooksResponse extends CoreResponse {

    private List<Book> books;

    public SearchBooksResponse(List<Book> books, List<CoreError> errors) {
        super(errors);
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}
