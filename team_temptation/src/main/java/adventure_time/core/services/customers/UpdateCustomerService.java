package adventure_time.core.services.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.requests.customers.UpdateCustomerRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.customer.UpdateCustomerResponse;
import adventure_time.core.database.customers.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateCustomerService {

    @Autowired
    private CustomerRepository database;
    @Autowired
    private UpdateCustomerRequestValidator validatorUpdate;

    public UpdateCustomerResponse updateCustomer (UpdateCustomerRequest request) {

        List<CoreError> errors = validatorUpdate.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateCustomerResponse(errors);
        }

        Customers customer = new Customers(
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                request.getPasswordOne()
        );
        customer.setCustomerId(request.getId());
        if (database.updateCustomer(customer, request.getId())) {
            return new UpdateCustomerResponse();
        }

        CoreError error = new CoreError("customerEmail", "Email " + customer.getCustomerEmail() + " is not valid");
        errors.add(error);
        return new UpdateCustomerResponse(errors);

    }

}
