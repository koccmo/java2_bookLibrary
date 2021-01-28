package adventure_time.core.services.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.requests.customers.LoginCustomerRequest;
import adventure_time.core.requests.customers.UpdateCustomerRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.customer.LoginCustomerResponse;
import adventure_time.core.responses.customer.UpdateCustomerResponse;
import adventure_time.database.customers.DatabaseCustomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateCustomerService {

    @Autowired
    private DatabaseCustomers database;
    @Autowired
    private LoginCustomerRequestValidator validatorLogin;
    @Autowired
    private UpdateCustomerRequestValidator validatorUpdate;

    public LoginCustomerResponse loginCustomer (LoginCustomerRequest request) {
        List<CoreError> errors = validatorLogin.validate(request);
        if (!errors.isEmpty()) {
            return new LoginCustomerResponse(errors);
        }

        //TODO всю проверку на возможные ошибки нахождения кастомера и совпадения паролей здесь!!!

        Long customerId = database.checkLoginBeforeUpdate(request.getEmail(), request.getPassword());
        if (customerId == -1) {
            CoreError error = new CoreError("customerEmail", "Wrong email");
            errors.add(error);
            return new LoginCustomerResponse(errors);
        }

        if (customerId == 0) {
            CoreError error = new CoreError("customerPassword", "Wrong password");
            errors.add(error);
            return new LoginCustomerResponse(errors);
        }

        Customers customer = database.findById(customerId).get();

        return new LoginCustomerResponse(customer);
    }

    public UpdateCustomerResponse updateCustomer (UpdateCustomerRequest request) {

        List<CoreError> errors = validatorUpdate.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateCustomerResponse(errors);
        }

        Customers customer = new Customers(request.getName(), request.getEmail(), request.getPhone(), request.getPasswordOne());
        customer.setCustomerID(request.getId());
        if (database.updateCustomer(customer, request.getId())) {
            return new UpdateCustomerResponse();
        }
        CoreError error = new CoreError("customerEmail", "Email " + customer.getCustomerEmail() + " is not valid");
        errors.add(error);
        return new UpdateCustomerResponse(errors);

    }

}
