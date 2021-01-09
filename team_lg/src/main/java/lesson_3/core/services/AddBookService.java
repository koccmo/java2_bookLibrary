package lesson_3.core.services;

import lesson_3.core.database.ElectronicLibrary;
import lesson_3.core.domain.Book;
import lesson_3.core.requests.AddBookRequest;
import lesson_3.core.responses.AddBookResponse;
import lesson_3.core.responses.CoreError;

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
