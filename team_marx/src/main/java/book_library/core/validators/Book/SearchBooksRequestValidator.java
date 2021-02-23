package book_library.core.validators.Book;

import book_library.core.requests.Ordering;
import book_library.core.requests.Paging;
import book_library.core.requests.Book.SearchBooksRequest;
import book_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SearchBooksRequestValidator {

    public List<CoreError> validate(SearchBooksRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateSearchFields(request));

        if (isOrderingRequested(request.getOrdering())) {
            validateOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateOrderDirection(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
        }
        if (isPagingRequested(request.getPaging())) {
                validatePageNumber(request.getPaging()).ifPresent(errors::add);
                validatePageSize(request.getPaging()).ifPresent(errors::add);
                validateMandatoryPageNumber(request.getPaging()).ifPresent(errors::add);
                validateMandatoryPageSize(request.getPaging()).ifPresent(errors::add);
        }
        return errors;
    }

    private List<CoreError> validateSearchFields(SearchBooksRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getTitle()) && isEmpty(request.getAuthor())) {
            errors.add(new CoreError("title", "Must not be empty"));
            errors.add(new CoreError("author", "Must not be empty"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isOrderingRequested(Ordering ordering) {
        if (ordering == null) {
            return false;
        } else {
            return !(ordering.getOrderBy() == null && ordering.getOrderDirection() == null);
        }
    }

    public static boolean isPagingRequested(Paging paging) {
        if (paging == null) {
            return false;
        } else {
            return !(paging.getPageNumber() == null && paging.getPageSize() == null);
        }
    }

    private Optional<CoreError> validateOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().equals("author") || ordering.getOrderBy().equals("title")))
                ? Optional.of(new CoreError("orderBy", "Must contain 'author' or 'title' only!"))
                : Optional.empty();
    }

    public static Optional<CoreError> validateOrderDirection(Ordering ordering) {
        return (ordering.getOrderDirection() != null
                && !(ordering.getOrderDirection().equals("ASCENDING") || ordering.getOrderDirection().equals("DESCENDING")))
                ? Optional.of(new CoreError("orderDirection", "Must contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

    public static Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() == null && ordering.getOrderDirection() != null)
                ? Optional.of(new CoreError("orderBy", "Must not be empty!"))
                : Optional.empty();
    }

    public static Optional<CoreError> validateMandatoryOrderDirection(Ordering ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "Must not be empty!"))
                : Optional.empty();
    }

    public static Optional<CoreError> validatePageNumber(Paging paging) {
        return (paging.getPageNumber() != null
                && paging.getPageNumber() <= 0)
                ? Optional.of(new CoreError("pageNumber", "Must be greater then 0!"))
                : Optional.empty();
    }

    public static Optional<CoreError> validatePageSize(Paging paging) {
        return (paging.getPageSize() != null
                && paging.getPageSize() <= 0)
                ? Optional.of(new CoreError("pageSize", "Must be greater then 0!"))
                : Optional.empty();
    }

    public static Optional<CoreError> validateMandatoryPageNumber(Paging paging) {
        return (paging.getPageNumber() == null && paging.getPageSize() != null)
                ? Optional.of(new CoreError("pageNumber", "Must not be empty!"))
                : Optional.empty();
    }

    public static Optional<CoreError> validateMandatoryPageSize(Paging paging) {
        return (paging.getPageSize() == null && paging.getPageNumber() != null)
                ? Optional.of(new CoreError("pageSize", "Must not be empty!"))
                : Optional.empty();
    }
}
