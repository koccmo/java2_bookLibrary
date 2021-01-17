package electronic_library.core.responses;

import electronic_library.core.domain.Book;

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
