package bookLibrary.core.service.validators;

import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.DeleteBookRequest;
import bookLibrary.core.response.CoreError;
import bookLibrary.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@DIComponent
public class DeleteBookValidation {
    public List<CoreError> validate (DeleteBookRequest request , DataBase dataBase) {
        List<CoreError> errors = new ArrayList<>();
        idNotEmptyValidate(request).ifPresent(errors::add);
        if (errors.isEmpty()) {
            bookInDataBase(request, dataBase).ifPresent(errors::add);
            idHasOnlyNumbersValidate(request).ifPresent(errors::add);
        }


        return errors;
    }

    private Optional<CoreError> idHasOnlyNumbersValidate(DeleteBookRequest request) {
        return (!isNumbers(request))
                ? Optional.of(new CoreError("Id", "Id must has only Numbers"))
                : Optional.empty();
    }

    private Optional<CoreError> idNotEmptyValidate(DeleteBookRequest  request) {
        return (fieldEmpty(request))
                ? Optional.of(new CoreError("ID", "Must be fill UP!"))
                : Optional.empty();
    }

    private Optional<CoreError> bookInDataBase(DeleteBookRequest request, DataBase dataBase) {
        if (isNumbers(request)) {
            boolean hasBookInLibrary = dataBase.hasBookInLibraryCheckById(Long.parseLong(request.getId()));
            return (!hasBookInLibrary)
                    ? Optional.of(new CoreError("Id", "Not found book with this ID!"))
                    : Optional.empty();
        } else return Optional.empty();
    }

    private boolean isNumbers (DeleteBookRequest request) {
        return request.getId().matches(".*[0-9].*");
    }

    private boolean fieldEmpty (DeleteBookRequest request) {
        return request.getId() == null || request.getId().isEmpty();
    }
}
