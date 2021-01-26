package adventure_time.core.services.customers;

import adventure_time.core.requests.customers.SearchCustomerRequest;
import adventure_time.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class SearchCustomerRequestValidator {

    private static final Pattern PATTERN_EMAIL = Pattern.compile("^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_ID = Pattern.compile("[0-9]+");

    public List<CoreError> validate (SearchCustomerRequest request) {

        List<CoreError> errors = new ArrayList<>();

        boolean id = !(request.getCustomerID() == null) && request.getCustomerEmail() == null;
        boolean email =  request.getCustomerID() == null && !(request.getCustomerEmail() == null);

        if (request.getCustomerID() == null && request.getCustomerEmail() == null) {
            CoreError error = new CoreError("searchCriteria", "It is required to define at list one of the search criteria. You did not define any.");
            errors.add(error);
        }

        if (request.getCustomerID() != null && request.getCustomerEmail() != null) {
            CoreError error = new CoreError("searchCriteria", "It is required to define only one of the search criteria. You defined both of them.");
            errors.add(error);
        }

        if (email && request.getCustomerEmail().isBlank()) {
            CoreError error = new CoreError("customerEmail", "Email must be defined.");
            errors.add(error);
        }

        if (email && !PATTERN_EMAIL.matcher(request.getCustomerEmail()).matches()) {
            CoreError error = new CoreError("customerEmail", "Email is incorrect.");
            errors.add(error);
        }

        if (id && !PATTERN_ID.matcher(request.getCustomerID().toString()).matches()) {
            CoreError error = new CoreError("customerID", "ID is incorrect.");
            errors.add(error);
        }

        return errors;
    }
}
