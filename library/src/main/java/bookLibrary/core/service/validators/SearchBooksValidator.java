package bookLibrary.core.service.validators;

import bookLibrary.core.request.Ordering;
import bookLibrary.core.request.Paging;
import bookLibrary.core.request.SearchBooksRequest;
import bookLibrary.core.response.CoreError;
import bookLibrary.dependency_injection.DIComponent;
import bookLibrary.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;
@DIComponent
public class SearchBooksValidator {
    @DIDependency private SearchBooksRequestFieldValidator fieldValidator;
    @DIDependency private OrderingValidator orderingValidator;

    @DIDependency private PagingValidator pagingValidator;


    public List<CoreError> validate(SearchBooksRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(fieldValidator.validate(request));
        validateOrderingIfPresent(request, errors);
        validationPagingIfPresent(request, errors);
        return errors;
    }

    private void validateOrderingIfPresent(SearchBooksRequest request, List<CoreError> errors) {
        if (request.getOrdering() != null) {
            Ordering ordering = request.getOrdering();
            errors.addAll(orderingValidator.validate(ordering));
        }
    }

    private void validationPagingIfPresent(SearchBooksRequest request, List<CoreError> errors) {
        if (request.getPaging() != null) {
            Paging paging = request.getPaging();
            errors.addAll(pagingValidator.validate(paging));
        }
    }

}
