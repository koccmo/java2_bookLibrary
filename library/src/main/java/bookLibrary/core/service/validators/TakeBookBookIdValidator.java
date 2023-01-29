package bookLibrary.core.service.validators;

import bookLibrary.core.dataBase.ReaderBookRepository;
import bookLibrary.core.request.TakeBookRequest;
import bookLibrary.core.response.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class TakeBookBookIdValidator {
    @Autowired
    private ReaderBookRepository repository;

    public List<CoreError> validate(TakeBookRequest request) {
        List errors = new ArrayList<>();
        bookIdNotEmpty(request).ifPresent(errors::add);
        bookIdContainOnlyNumbers(request).ifPresent(errors::add);
        if(errors.isEmpty()) {
            hasBookInDataBase(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> hasBookInDataBase(TakeBookRequest request) {
        return (repository.hasBookInDataBaseCheckById(Long.valueOf(request.getBookId())))
                ? Optional.empty()
                : Optional.of(new CoreError("BookId", "Book not found in DataBase"));
    }

    private Optional<CoreError> bookIdNotEmpty(TakeBookRequest request) {
        return (request.getBookId().isEmpty() || request.getBookId() == null)
                ? Optional.of(new CoreError("BookID", "Must be filled UP!"))
                : Optional.empty();
    }

    private Optional<CoreError> bookIdContainOnlyNumbers(TakeBookRequest request) {
        return (request.getBookId().chars().allMatch(Character::isDigit))
                ? Optional.empty()
                : Optional.of(new CoreError("BookID", "Must contain only Digit!"));
    }
}
