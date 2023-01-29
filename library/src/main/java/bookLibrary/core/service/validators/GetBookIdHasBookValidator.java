package bookLibrary.core.service.validators;

import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.domain.Book;
import bookLibrary.core.request.GetBookIdRequest;
import bookLibrary.core.response.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetBookIdHasBookValidator {
    @Autowired
    private DataBase dataBase;

    public List<CoreError> validate(GetBookIdRequest request) {
        List<CoreError> errors = new ArrayList<>();
        hasBookInDataBaseValidator(request).ifPresent(errors::add);
        return errors;
    }
    private Optional<CoreError> hasBookInDataBaseValidator(GetBookIdRequest request) {
        return (dataBase.hasBookInLibrary(new Book(request.getAuthor(), request.getTitle())))
                ? Optional.empty()
                : Optional.of(new CoreError("Book", "Book not found in DataBase"));
    }
}
