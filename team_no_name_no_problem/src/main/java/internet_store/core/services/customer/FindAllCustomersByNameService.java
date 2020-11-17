package internet_store.core.services.customer;

import internet_store.core.requests.customer.FindAllCustomersByNameRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.FindAllCustomersByNameResponse;
import internet_store.database.customer.CustomerDatabase;

import java.util.ArrayList;
import java.util.List;

public class FindAllCustomersByNameService {

    private final CustomerDatabase customerDatabase;
    private final FindAllCustomersByNameValidator findAllCustomersByNameValidator;

    public FindAllCustomersByNameService(CustomerDatabase customerDatabase, FindAllCustomersByNameValidator findAllCustomersByNameValidator) {
        this.customerDatabase = customerDatabase;
        this.findAllCustomersByNameValidator = findAllCustomersByNameValidator;
    }

    public FindAllCustomersByNameResponse execute(FindAllCustomersByNameRequest findAllCustomersByNameRequest) {

        List<CoreError> errors = findAllCustomersByNameValidator.validate(findAllCustomersByNameRequest);
        if (!errors.isEmpty()) {
            return new FindAllCustomersByNameResponse(errors, new ArrayList<>());
        }

        if (customerDatabase.findAllCustomersByName(findAllCustomersByNameRequest.getName()).isEmpty()){
            errors.add(new CoreError("database","Database doesn't contain such customer name"
            + findAllCustomersByNameRequest.getName()));
            return new FindAllCustomersByNameResponse(errors, new ArrayList<>());
        }
        return new FindAllCustomersByNameResponse(customerDatabase.findAllCustomersByName
                (findAllCustomersByNameRequest.getName()));
    }
}
