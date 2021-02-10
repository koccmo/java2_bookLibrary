package lesson_12.core.services.book;

import lesson_12.core.database.book.ElectronicLibraryRepository;
import lesson_12.core.domain.Book;
import lesson_12.core.requests.book.AddBookRequest;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.book.AddBookResponse;
import lesson_12.core.services.book.validators.AddBookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddBookService {

    @Autowired
    private ElectronicLibraryRepository electronicLibrary;

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
