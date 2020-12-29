package book_library.core.validators;

import book_library.core.requests.Ordering;
import book_library.core.requests.SearchBooksRequest;
import book_library.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchBooksRequestValidator {

    public List<CoreError> validate (SearchBooksRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateSearchFields(request));
        if(request.getOrdering() != null){
            if (request.getOrdering().getOrderBy() == null && request.getOrdering().getOrderDirection() == null){
                return errors;
            } else {
                validateOrderBy(request.getOrdering()).ifPresent(errors::add);
                validateOrderDirection(request.getOrdering()).ifPresent(errors::add);
                validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
                validateMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
            }
        }
        return errors;
    }

    private List<CoreError> validateSearchFields(SearchBooksRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getTitle()) && isEmpty(request.getAuthor())){
            errors.add(new CoreError("title", "Must not be empty"));
            errors.add(new CoreError("author", "Must not be empty"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {return str == null || str.isEmpty();}

    private Optional<CoreError> validateOrderBy(Ordering ordering) {
        if(ordering.getOrderBy() == null)
            return Optional.empty();
        return (!ordering.getOrderBy().isEmpty()
                && !(ordering.getOrderBy().equals("author") || ordering.getOrderBy().equals("title")))
                ? Optional.of(new CoreError("orderBy", "Must contain 'author' or 'title' only!"))
                : Optional.empty();

    }

    private Optional<CoreError> validateOrderDirection(Ordering ordering) {
        if(ordering.getOrderDirection() == null)
            return Optional.empty();
        return (!ordering.getOrderDirection().isEmpty()
                && !(ordering.getOrderDirection().equals("ASCENDING") || ordering.getOrderDirection().equals("DESCENDING")))
                ? Optional.of (new CoreError("orderDirection", "Must contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
        if (ordering.getOrderBy() == null && ordering.getOrderDirection() != null )
                return Optional.of (new CoreError("orderBy", "Must not be empty!"));
        if (ordering.getOrderBy() != null && ordering.getOrderDirection() != null )
            return (ordering.getOrderBy().isEmpty())
                    ? Optional.of (new CoreError("orderBy", "Must not be empty!"))
                    : Optional.empty();
        return Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderDirection(Ordering ordering) {
        if (ordering.getOrderDirection() == null && ordering.getOrderBy() != null )
            return Optional.of (new CoreError("orderDirection", "Must not be empty!"));
        if (ordering.getOrderDirection() != null && ordering.getOrderBy() != null )
            return (ordering.getOrderDirection().isEmpty())
                    ? Optional.of (new CoreError("orderBy", "Must not be empty!"))
                    : Optional.empty();
        return Optional.empty();
    }
}
