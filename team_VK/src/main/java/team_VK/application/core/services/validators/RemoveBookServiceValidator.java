package team_VK.application.core.services.validators;

import org.springframework.stereotype.Component;
import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.database.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class RemoveBookServiceValidator {


    public List<CoreError> validate(RemoveBookRequest request, BookRepository database) {
        List<CoreError> errors = new ArrayList<>();



        Optional<Book> removableBook = database.getListBooks().stream()
                .filter(book -> book.getID() == request.getBookId())
                .findFirst();
        if (removableBook.isEmpty()) {
            errors.add(new CoreError("Book ID", "Not existing book ID entered"));
        }
        if (removableBook.isPresent() && !removableBook.get().bookTitle.equals(request.getBookTitle())) {
            errors.add(new CoreError("Book ID", "ID not consist to Book Title"));
        }

        return errors;
    }

}
