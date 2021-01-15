package adventure_time.core.services.customers;

import adventure_time.core.requests.customers.SearchCustomerRequest;
import adventure_time.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchCustomerRequestValidator {

    private final static int MIN_PAGE_SIZE = 8;
    private final static int MAX_PAGE_SIZE = 30;

    public List<CoreError> validate (SearchCustomerRequest request) {

        List<CoreError> errors = new ArrayList<>();


        return errors;
    }
}
