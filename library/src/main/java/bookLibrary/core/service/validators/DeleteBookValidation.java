package bookLibrary.core.service.validators;

import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.DeleteBookRequest;
import bookLibrary.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class DeleteBookValidation {
    public List<CoreError> validate (DeleteBookRequest request , DataBase dataBase) {
        List<CoreError> errors = new ArrayList<>();
        idNotEmptyValidate(request).ifPresent(errors::add);
        idHasOnlyNumbersValidate(request).ifPresent(errors::add);
        if (errors.isEmpty()) {
            bookInDataBase(request, dataBase).ifPresent(errors::add);
        }


        return errors;
    }

    private Optional<CoreError> idHasOnlyNumbersValidate(DeleteBookRequest request) {
        return (isNumbers(request))
                ? Optional.empty()
                :Optional.of(new CoreError("Id", "Id must has only Numbers"));
    }

    private Optional<CoreError> idNotEmptyValidate(DeleteBookRequest  request) {
        return (fieldEmpty(request))
                ? Optional.of(new CoreError("ID", "Must be fill UP!"))
                : Optional.empty();
    }

    private Optional<CoreError> bookInDataBase(DeleteBookRequest request, DataBase dataBase) {
        boolean hasBookInLibrary = dataBase.hasBookInLibraryCheckById(Long.parseLong(request.getId()));
        return (!hasBookInLibrary)
                ? Optional.of(new CoreError("Id", "Not found book with this ID!"))
                : Optional.empty();
//        if (isNumbers(request)) {
//            boolean hasBookInLibrary = dataBase.hasBookInLibraryCheckById(Long.parseLong(request.getId()));
//            return (!hasBookInLibrary)
//                    ? Optional.of(new CoreError("Id", "Not found book with this ID!"))
//                    : Optional.empty();
//        } else return Optional.empty();
    }

    private boolean isNumbers (DeleteBookRequest request) {
        return request.getId().chars().allMatch(Character::isDigit);
    }

    private boolean fieldEmpty (DeleteBookRequest request) {
        return request.getId() == null || request.getId().isEmpty();
    }
}
