package book_library.core.responses.Book;

import book_library.core.domain.Book;
import book_library.core.responses.CoreResponse;

import java.util.List;

public class GetAllBooksResponse extends CoreResponse {

    private List<Book> books;

    public GetAllBooksResponse(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

}
