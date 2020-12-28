package book_library.core.responses;

import book_library.Book;

import java.util.List;

public class SearchBooksResponse extends CoreResponse{

    private List<Book> books;

    public SearchBooksResponse(List<Book> books, List<CoreError> errors) {
        super(errors);
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}
