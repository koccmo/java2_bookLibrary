package core.services.events;

import core.requests.events.SearchEventRequest;
import core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class SearchEventRequestValidator {

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

        if (request.getPaging().getPageSize() < 8 || request.getPaging().getPageSize() > 30) {
            // error
            CoreError error = new CoreError("pageSize", "Must be between 8 and 30");
            errors.add(error);
        }
        return errors;
    }

}
