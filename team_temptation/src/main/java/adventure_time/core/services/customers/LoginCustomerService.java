package adventure_time.core.services.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.requests.customers.LoginCustomerRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.customer.LoginCustomerResponse;
import adventure_time.database.customers.DatabaseCustomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LoginCustomerService {

    @Autowired
    private DatabaseCustomers database;
    @Autowired
    private LoginCustomerRequestValidator validator;

    public LoginCustomerResponse loginCustomer (LoginCustomerRequest request) {

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new LoginCustomerResponse(errors);
        }

        Optional<Customers> customer = database.findByEmail(request.getEmail());

        return checkLogin(customer, request);
    }

    private LoginCustomerResponse checkLogin(Optional<Customers> customer, LoginCustomerRequest request) {
        if (customer.isPresent()) {
            if (customer.get().getCustomerPassword().equals(request.getEmail())) {
                return new LoginCustomerResponse(customer.get());   // customer found, passwords matched
            } else {
                return new LoginCustomerResponse(getErrorMessage(
                        "customerPassword",
                        "Passwords did not match"));   // customer found, passwords mismatched
            }
        } else {
            return new LoginCustomerResponse(getErrorMessage(
                    "customerEmail",
                    "Customer not found"));   // customer not found
        }
    }

    private List<CoreError> getErrorMessage(String field, String message) {
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError(field, message));
        return errors;
    }


}
