package adventure_time.core.services.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.requests.customers.AddCustomerRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.customer.AddCustomerResponse;
import adventure_time.database.customers.DatabaseCustomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddCustomerService {

    @Autowired
    private DatabaseCustomers database;
    @Autowired
    private AddCustomerRequestValidator validator;

    public AddCustomerResponse addCustomer (AddCustomerRequest request) {

        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new AddCustomerResponse (errors);
        }

        Customers customer = new Customers(request.getCustomerName(), request.getCustomerEmail(), request.getCustomerPhone(), request.getPasswordOne());
        if (database.add(customer)) return new AddCustomerResponse();

        errors.add(new CoreError("customerEmail", "The user under login \"" + request.getCustomerEmail() + "\" already exist."));
        return new AddCustomerResponse (errors);
    }
}
