package team_VK.application.core.services.main_menu_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.responses.AddBookResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.validators.AddBookServiceValidator;
import team_VK.application.database.BookRepository;

import java.util.List;

@Component
@Transactional
public class AddBookService {

    @Autowired
    private BookRepository database;
    @Autowired public AddBookServiceValidator validator;


    public AddBookResponse addBook(AddBookRequest request) {

        List<CoreError> errors = validator.validate(request);

        if (errors.size() == 0) {
            Book book = new Book(request.bookTitle, request.bookAuthor, request.bookingDurationPermitted);
            database.addBook(book);
        }
        return new AddBookResponse(errors);

    }

}
