package electronic_library.core.responses.book;

import electronic_library.core.domain.Book;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

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
