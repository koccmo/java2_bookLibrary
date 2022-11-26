package bookLibrary.core.service.validators;

import bookLibrary.core.request.Paging;
import bookLibrary.core.response.CoreError;
import bookLibrary.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@DIComponent
public class PagingValidator {

    public List<CoreError> validate(Paging paging) {
        List<CoreError> errors = new ArrayList<>();
        validationPageNumber(paging).ifPresent(errors::add);
        validationPageSize(paging).ifPresent(errors::add);
        validationPageNumberEnteredPageSizeNo(paging).ifPresent(errors::add);
        validationPageSizeEnteredPageNumberNo(paging).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validationPageNumberEnteredPageSizeNo(Paging paging) {
        return (paging.getPageNumber() != null && paging.getPageSize() == null)
                ? Optional.of(new CoreError("PageSize", "Must be fill UP!"))
                : Optional.empty();
    }

    private Optional<CoreError> validationPageSizeEnteredPageNumberNo(Paging paging) {
        return (paging.getPageSize() != null && paging.getPageNumber() == null)
                ? Optional.of(new CoreError("PageNumber", "Must be fill UP!"))
                : Optional.empty();
    }

    private Optional<CoreError> validationPageNumber(Paging paging) {
        return (paging.getPageNumber() != null && paging.getPageNumber() <= 0)
                ? Optional.of(new CoreError("PageNumber", "Must be over 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validationPageSize(Paging paging) {
        return (paging.getPageSize() != null && paging.getPageSize() <= 0)
                ? Optional.of(new CoreError("PageSize", "Must be over 0!"))
                : Optional.empty();
    }
}
