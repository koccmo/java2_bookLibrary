package lesson_12.core.responses.book;

import lesson_12.core.domain.Book;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

import java.util.List;

public class GetAllBooksResponse extends CoreResponse {

    private List<Book> books;

    public GetAllBooksResponse(List<Book> books) {
        this.books = books;
    }

    public GetAllBooksResponse(List<CoreError> errors, List<Book> books) {
        super(errors);
    }

    public List<Book> getBooks() {
        return books;
    }

}
