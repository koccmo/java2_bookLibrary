package bookLibrary.core.response;

import bookLibrary.core.domain.Book;

import java.util.List;

public class FindByAuthorResponse extends CoreResponse{
    private List<Book> bookList;


    public FindByAuthorResponse(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }
}
