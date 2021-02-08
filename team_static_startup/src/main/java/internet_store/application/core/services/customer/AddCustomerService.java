package internet_store.application.core.services.customer;

import internet_store.application.core.database.customer.CustomerRepository;
import internet_store.application.core.domain.Customer;
import internet_store.application.core.requests.customer.AddCustomerRequest;
import internet_store.application.core.responses.customer.AddCustomerResponse;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.services.customer.validators.AddCustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddCustomerService {

    @Autowired private CustomerRepository customerRepository;
    @Autowired private AddCustomerValidator validator;

    public AddCustomerResponse execute(AddCustomerRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddCustomerResponse(errors);
        }
        Customer customer = new Customer(request.getCustomerFirstName(), request.getCustomerSecondName());
        customer.setCustomerPhone(request.getCustomerPhone());
        customer.setCustomerEmail(request.getCustomerEMail());
        customer.setCustomerAddress(request.getCustomerAddress());

        customerRepository.addCustomer(customer);
        return new AddCustomerResponse(customer);
    }
}
