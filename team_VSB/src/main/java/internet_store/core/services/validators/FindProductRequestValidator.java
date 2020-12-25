package internet_store.core.services.validators;


import internet_store.core.requests.FindProductRequest;
import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindProductRequestValidator {

    public static final String ORDERING_NAME_1 = "Name";
    public static final String ORDERING_NAME_2 = "Description";
    public static final String ORDERING_DIRECTION_1 = "Ascending";
    public static final String ORDERING_DIRECTION_2 = "Descending";

    public List<CoreError> validate(FindProductRequest request) {
       return new ArrayList<>(validateSearchFields(request));
    }

    private List<CoreError> validateSearchFields(FindProductRequest request) {
        Ordering ordering = request.getOrdering();
        Paging paging = request.getPaging();
        List<CoreError> errors = new ArrayList<>();

        if (isEmpty(request.getProductName()) && isEmpty(request.getProductDescription())) {
            errors.add(new CoreError("Name", "Can't be empty!"));
            errors.add(new CoreError("Description", "Can't be empty!"));
        }

        if (paging != null) {
            if (!bothFieldsAreEmpty(paging) && !bothFieldsAreFilled(paging)) {
                errors.add(new CoreError("Page number and size",
                        "Both this field must be empty of filled!"));
            }
            if (paging.getPageNumber() != null && paging.getPageNumber() <= 0) {
                errors.add(new CoreError("Page number", "Must be bigger than zero!"));
            }
            if (paging.getPageSize() != null && paging.getPageSize() <= 0) {
                errors.add(new CoreError("Page size", "Must be bigger than zero!"));
            }
        }

    if (ordering != null) {
        if (!isEmpty(ordering.getOrderBy()) || isEmpty(ordering.getOrderDirection())) {
            errors.add(new CoreError("Ordering fields",
                    "Both this fields must be empty or filled!")); }
        if (inCorrectOrderName(ordering)) {
            errors.add(new CoreError("Ordering", "Must be name or description!")); }
        if (inCorrectOrderDescription(ordering)) {
            errors.add(new CoreError("Description", "Must be ascending or descending")); }
    }
        return errors;
    }

    private boolean isEmpty(String string) { return string == null || string.isEmpty(); }

    private boolean inCorrectOrderName(Ordering ordering) {
        return !ORDERING_NAME_1.equalsIgnoreCase(ordering.getOrderBy())
                && !ORDERING_NAME_2.equalsIgnoreCase(ordering.getOrderBy());
    }

    private boolean inCorrectOrderDescription(Ordering ordering) {
        return !ORDERING_DIRECTION_1.equalsIgnoreCase(ordering.getOrderDirection())
                && !ORDERING_DIRECTION_2.equalsIgnoreCase(ordering.getOrderDirection());
    }

    private boolean bothFieldsAreEmpty(Paging paging) {
        return ((paging.getPageSize() == null) || (paging.getPageNumber() == null ));
    }

    private boolean bothFieldsAreFilled(Paging paging) {
        return ((paging.getPageSize() != null) || (paging.getPageNumber() != null ));
    }
}
