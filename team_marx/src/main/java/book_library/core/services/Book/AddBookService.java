package book_library.core.services.Book;

import book_library.core.domain.Book;
import book_library.core.database.Book.BookRepository;
import book_library.core.requests.Book.AddBookRequest;
import book_library.core.responses.Book.AddBookResponse;
import book_library.core.responses.CoreError;
import book_library.core.validators.Book.AddBookRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Component
@Transactional
public class AddBookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired private AddBookRequestValidator validator;

    @Transactional
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
