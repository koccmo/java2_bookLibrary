package book_library.core.responses;

import book_library.core.domain.Book;
import java.util.List;

public class GetAllBooksResponse {

    private List<Book> books;

    public GetAllBooksResponse(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}
