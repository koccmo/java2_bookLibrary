package book_library.core.services;

import book_library.core.domain.Book;
import book_library.core.requests.AddBookRequest;
import book_library.core.responses.AddBookResponse;
import book_library.core.responses.CoreError;
import book_library.database.ElectronicLibrary;

import java.util.List;

public class AddBookService {

    private ElectronicLibrary electronicLibrary;
    private AddBookValidator validator;

    public AddBookService(ElectronicLibrary electronicLibrary,
                          AddBookValidator validator) {
        this.electronicLibrary = electronicLibrary;
        this.validator = validator;
    }

    public AddBookResponse execute(AddBookRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddBookResponse(errors);
        }

        Book book = new Book(request.getBookTitle(), request.getBookAuthor(),
                request.getBookPrice(),request.getYearOfBookIssue());
        electronicLibrary.saveBook(getBook(book));

        return new AddBookResponse(book);
    }

    private Book getBook(Book book) {
        return book;
    }
}
