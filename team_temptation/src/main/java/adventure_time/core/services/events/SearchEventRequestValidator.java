package adventure_time.core.services.events;

import adventure_time.core.requests.events.SearchEventRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.dependencies.DIComponent;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class SearchEventRequestValidator {

    private final static int MIN_PAGE_SIZE = 8;
    private final static int MAX_PAGE_SIZE = 30;

    public List<CoreError> validate (SearchEventRequest request) {

        // Validation
        // errors!
        List<CoreError> errors = new ArrayList<>();

        // mandatory: if orderBy is defined then orderDirect must be defined too
        if ((!request.getOrdering().getOrderBy().equals(""))
                && request.getOrdering().getOrderDirection().equals("")) {
            // error
            CoreError error = new CoreError("orderDirect", "Must be specified as the sort criteria is specified");
            errors.add(error);
        }
        // mandatory: if orderBy is not defined then orderDirect must be not defined too
        if (request.getOrdering().getOrderBy().equalsIgnoreCase("")
                && (!request.getOrdering().getOrderDirection().equalsIgnoreCase(""))) {
            // error
            CoreError error = new CoreError("orderBy", "Must be specified as the sort order is specified");
            errors.add(error);
        }

        if (request.getPaging().getPageNumber() <= 0) {
            // error
            CoreError error = new CoreError("pageNumber", "Must be above zero");
            errors.add(error);
        }

        if (request.getPaging().getPageSize() < MIN_PAGE_SIZE || request.getPaging().getPageSize() > MAX_PAGE_SIZE) {
            // error
            CoreError error = new CoreError("pageSize", "Must be between 8 and 30");
            errors.add(error);
        }
        return errors;
    }

}
