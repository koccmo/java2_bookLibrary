package electronic_library.core.services;

import electronic_library.core.database.ElectronicLibrary;
import electronic_library.core.domain.Book;
import electronic_library.core.requests.AddBookRequest;
import electronic_library.core.responses.AddBookResponse;
import electronic_library.core.responses.CoreError;
import electronic_library.core.services.validators.AddBookValidator;
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
