package book_library.core.validators.Reader;

import book_library.core.requests.Ordering;
import book_library.core.requests.Reader.SearchReaderRequest;
import book_library.core.responses.CoreError;
import book_library.core.validators.Book.SearchBooksRequestValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SearchReadersRequestValidator {

    public List<CoreError> validate(SearchReaderRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateSearchFields(request));

        if(request.getPersonalCode() != null) {
            validatePersonalCode(request).ifPresent(errors::add);
        }

        if(SearchBooksRequestValidator.isOrderingRequested(request.getOrdering())){
            validateOrderBy(request.getOrdering()).ifPresent(errors::add);
            SearchBooksRequestValidator.validateOrderDirection(request.getOrdering()).ifPresent(errors::add);
            SearchBooksRequestValidator.validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
            SearchBooksRequestValidator.validateMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
        }
        if(SearchBooksRequestValidator.isPagingRequested(request.getPaging())){
            SearchBooksRequestValidator.validatePageNumber(request.getPaging()).ifPresent(errors::add);
            SearchBooksRequestValidator.validatePageSize(request.getPaging()).ifPresent(errors::add);
            SearchBooksRequestValidator.validateMandatoryPageNumber(request.getPaging()).ifPresent(errors::add);
            SearchBooksRequestValidator.validateMandatoryPageSize(request.getPaging()).ifPresent(errors::add);
        }
        return errors;
    }

    private List<CoreError> validateSearchFields(SearchReaderRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getFirstName()) && isEmpty(request.getLastName()) && request.getPersonalCode() == null) {
            errors.add(new CoreError("firstName", "Must not be empty"));
            errors.add(new CoreError("lastName", "Must not be empty"));
            errors.add(new CoreError("personalCode", "Must not be empty"));
        }
        return errors;
    }

    private Optional<CoreError> validatePersonalCode(SearchReaderRequest request) {
        return (request.getPersonalCode().toString().length() == 11)
                ? Optional.empty()
                : Optional.of(new CoreError("personalCode", "Must contain 11 digits"));
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private Optional<CoreError> validateOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().equals("firstName") || ordering.getOrderBy().equals("lastName") || ordering.getOrderBy().equals("personalCod")))
                ? Optional.of(new CoreError("orderBy", "Must contain 'firstName', 'lastName' or 'personalCode' only!"))
                : Optional.empty();
    }

}
