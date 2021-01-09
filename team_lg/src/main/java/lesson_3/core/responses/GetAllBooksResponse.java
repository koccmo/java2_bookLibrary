package lesson_3.core.responses;

import lesson_3.core.domain.Book;

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
