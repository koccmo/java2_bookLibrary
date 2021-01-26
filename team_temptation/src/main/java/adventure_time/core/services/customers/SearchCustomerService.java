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
import java.util.Optional;

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

        Optional<Customers> customer;
        if (request.getCustomerID() == null) {
            customer = database.findByEmail(request.getCustomerEmail());
        } else {
            customer = database.findById(request.getCustomerID());
        }

        if (customer.isPresent()) {
            return new SearchCustomerResponse(customer.get(), null);
        } else {
            String field;
            String criteria;
            if (request.getCustomerID() == null) {
                field = "customerEmail";
                criteria = request.getCustomerEmail();
            } else {
                field = "customerID";
                criteria = request.getCustomerID().toString();
            }

            CoreError error = new CoreError(field, "The customer with " + field + " \"" + criteria + "\" is not present in DB");
            errors.add(error);
            return new SearchCustomerResponse(null, errors);
        }
    }

}
