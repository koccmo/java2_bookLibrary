package internet_store.application.core.services.validators;

import internet_store.application.core.requests.FindProductsRequest;
import internet_store.application.core.responses.CoreError;

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
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getName()) && isEmpty(request.getDescription())) {
            errors.add(new CoreError("Name", "Must not be empty!"));
            errors.add(new CoreError("Description", "Must not be empty!"));
        } else if (!isEmpty(request.getOrderBy()) || !isEmpty(request.getOrderDirection())) {
            if (isEmpty(request.getOrderBy()) || isEmpty(request.getOrderDirection())) {
                errors.add(new CoreError("Ordering Fields", "Both must be empty or filled!"));
            } else if (inCorrectOrderingNames(request)) {
                errors.add(new CoreError("Ordering by", "Must be Name or Description."));
            } else if (inCorrectOrderingDirection(request)) {
                errors.add(new CoreError("Direction", "Must be Ascending or Descending."));
            }
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean inCorrectOrderingNames(FindProductsRequest request) {
        return !ORDERING_NAME_1.equalsIgnoreCase(request.getOrderBy())
                && !ORDERING_NAME_2.equalsIgnoreCase(request.getOrderBy());
    }

    private boolean inCorrectOrderingDirection(FindProductsRequest request) {
        return !ORDERING_DIRECTION_1.equalsIgnoreCase(request.getOrderDirection())
                && !ORDERING_DIRECTION_2.equalsIgnoreCase(request.getOrderDirection());

    }


}
