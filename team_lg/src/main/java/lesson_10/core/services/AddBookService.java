package lesson_10.core.services;

import lesson_10.core.database.ElectronicLibrary;
import lesson_10.core.domain.Book;
import lesson_10.core.requests.AddBookRequest;
import lesson_10.core.responses.AddBookResponse;
import lesson_10.core.responses.CoreError;
import lesson_10.core.services.validators.AddBookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddBookService {

    @Autowired
    private ElectronicLibrary electronicLibrary;

    @Autowired
    private AddBookValidator validator;

    public AddBookResponse execute(AddBookRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddBookResponse(errors);
        }

        Book book = new Book(request.getBookTitle(), request.getBookAuthor(),
                request.getBookPrice(), request.getYearOfBookIssue());
        electronicLibrary.saveBook(getBook(book));

        return new AddBookResponse(book);
    }

    private Book getBook(Book book) {
        return book;
    }
}
