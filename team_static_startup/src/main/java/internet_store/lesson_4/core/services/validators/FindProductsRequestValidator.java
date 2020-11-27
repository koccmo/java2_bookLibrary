package internet_store.lesson_4.core.services.validators;

import internet_store.lesson_4.core.requests.FindProductsRequest;
import internet_store.lesson_4.core.requests.Ordering;
import internet_store.lesson_4.core.requests.Paging;
import internet_store.lesson_4.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindProductsRequestValidator {

    public static final String ORDERING_NAME_1 = "Name";
    public static final String ORDERING_NAME_2 = "Description";
    public static final String ORDERING_DIRECTION_1 = "Ascending";
    public static final String ORDERING_DIRECTION_2 = "Descending";

    public List<CoreError> validate(FindProductsRequest request) {
        return new ArrayList<>(validateSearchFields(request));
    }

    private List<CoreError> validateSearchFields(FindProductsRequest request) {
        Ordering ordering = request.getOrdering();
        Paging paging = request.getPaging();
        List<CoreError> errors = new ArrayList<>();

        if (isEmpty(request.getName()) && isEmpty(request.getDescription())) {
            errors.add(new CoreError("Name", "Must not be empty!"));
            errors.add(new CoreError("Description", "Must not be empty!"));
        }

        if (paging != null) {
            if (!bothFieldsAreEmpty(paging) && !bothFieldsAreFilled(paging)) {
                errors.add(new CoreError("Page number and page size"
                        , "Both must be empty or filled.")); }
            if (paging.getPageSize() != null && paging.getPageSize() <= 0) {
                errors.add(new CoreError("Page size", "Must be bigger than zero.")); }

            if (paging.getPageNumber() != null && paging.getPageNumber() <= 0) {
                errors.add(new CoreError("Page number", "Must be bigger than zero.")); }
        }

        if (ordering != null) {
            if (isEmpty(ordering.getOrderBy()) || isEmpty(ordering.getOrderDirection())) {
                errors.add(new CoreError("Ordering Fields"
                        , "Both must be empty or filled!")); }

            if (inCorrectOrderingNames(ordering)) {
                errors.add(new CoreError("Ordering by"
                        , "Must be Name or Description.")); }

            if (inCorrectOrderingDirection(ordering)) {
                errors.add(new CoreError("Direction"
                        , "Must be Ascending or Descending.")); }
        } return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean inCorrectOrderingNames(Ordering ordering) {
        return !ORDERING_NAME_1.equalsIgnoreCase(ordering.getOrderBy())
                && !ORDERING_NAME_2.equalsIgnoreCase(ordering.getOrderBy());
    }

    private boolean inCorrectOrderingDirection(Ordering ordering) {
        return !ORDERING_DIRECTION_1.equalsIgnoreCase(ordering.getOrderDirection())
                && !ORDERING_DIRECTION_2.equalsIgnoreCase(ordering.getOrderDirection());
    }

    public boolean bothFieldsAreEmpty (Paging paging) {
        return ((paging.getPageNumber() == null) && (paging.getPageSize() == null));
    }

    public boolean bothFieldsAreFilled (Paging paging) {
        return ((paging.getPageNumber() != null) && (paging.getPageSize() != null));
    }


}
