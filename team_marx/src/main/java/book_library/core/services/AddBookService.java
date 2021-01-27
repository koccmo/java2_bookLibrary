package book_library.core.services;

import book_library.core.domain.Book;
import book_library.core.database.BookRepository;
import book_library.core.requests.AddBookRequest;
import book_library.core.responses.AddBookResponse;
import book_library.core.responses.CoreError;
import book_library.core.validators.AddBookRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddBookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired private AddBookRequestValidator validator;

    public AddBookResponse execute(AddBookRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddBookResponse(errors);
        }
        Book book = new Book(request.getTitle(), request.getAuthor());
        bookRepository.save(book);

        return new AddBookResponse(book);
    }
}
