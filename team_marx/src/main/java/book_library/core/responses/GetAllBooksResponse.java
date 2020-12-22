package book_library.core.responses;

import book_library.Book;

import java.util.List;

public class GetAllBooksResponse extends CoreResponse{

    private List<Book> books;

    public GetAllBooksResponse(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}
