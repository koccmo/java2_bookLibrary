package bookLibrary.core.response;

import bookLibrary.core.domain.Book;

import java.util.List;

public class SearchBooksResponse extends CoreResponse{
    private List<Book> foundedBookList;

    public SearchBooksResponse(List<CoreError> errors, List<Book> foundedBookList) {
        super(errors);
        this.foundedBookList = foundedBookList;
    }

    public List<Book> getFoundedBookList() {
        return foundedBookList;
    }
}
