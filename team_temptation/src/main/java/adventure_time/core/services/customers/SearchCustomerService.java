package adventure_time.core.services.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.requests.customers.SearchCustomerRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.customer.SearchCustomerResponse;
import adventure_time.database.customers.DatabaseCustomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchCustomerService {

    @Autowired
    private DatabaseCustomers database;
    @Autowired
    SearchCustomerRequestValidator validator;

    public SearchCustomerResponse searchCustomer (SearchCustomerRequest request) {

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchCustomerResponse(null, errors);
        }

        List<Customers> customers = new ArrayList<>();






        return new SearchCustomerResponse(null, errors);
    }

}
