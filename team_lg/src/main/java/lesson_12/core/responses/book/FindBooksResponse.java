package lesson_12.core.responses.book;

import lesson_12.core.domain.Book;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

import java.util.List;

public class FindBooksResponse extends CoreResponse {

    private List<Book> books;

    public FindBooksResponse(List<Book> books, List<CoreError> errors) {
        super(errors);
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

}
