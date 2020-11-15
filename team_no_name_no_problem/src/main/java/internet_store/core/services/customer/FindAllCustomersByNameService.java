package internet_store.core.services.customer;

import internet_store.core.requests.customer.FindCustomerByNameRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.AddCustomerResponse;
import internet_store.core.response.customer.FindCustomerByNameResponse;
import internet_store.database.customer.CustomerDatabase;
import internet_store.core.domain.Customer;

import java.util.List;
import java.util.Optional;

public class FindAllCustomersByNameService {

    private final CustomerDatabase customerDatabase;
    private final FindCustomerByNameValidator findCustomerByNameValidator;

    public FindAllCustomersByNameService(CustomerDatabase customerDatabase, FindCustomerByNameValidator findCustomerByNameValidator) {
        this.customerDatabase = customerDatabase;
        this.findCustomerByNameValidator = findCustomerByNameValidator;
    }

    public FindCustomerByNameResponse execute(FindCustomerByNameRequest findCustomerByNameRequest){

        List<CoreError> errors = findCustomerByNameValidator.validate(findCustomerByNameRequest);
        if (!errors.isEmpty()){
            return new FindCustomerByNameResponse(errors);
        }

        List<Customer> expectedCustomer = customerDatabase.findAllCustomersByName(findCustomerByNameRequest.getName());
        if (expectedCustomer.isEmpty()){
            errors.add(new CoreError("database","Database doesn't contain this name"
            + findCustomerByNameRequest.getName()));
            return new FindCustomerByNameResponse(errors);
        }
        return new FindCustomerByNameResponse(customerDatabase.findAllCustomersByName(
                findCustomerByNameRequest.getName()).get(1));
    }
}
