package internet_store.core.services.customer;

import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.FindCustomerByIdRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.FindCustomerByIdResponse;
import internet_store.core.services.customer.validators.FindCustomerByIdRequestValidator;
import internet_store.database.customer.CustomerDatabase;
import internet_store.dependency_injection.DIComponent;
import internet_store.dependency_injection.DIDependency;

import java.util.List;
import java.util.Optional;

@DIComponent
public class FindCustomerByIdService {

    @DIDependency private CustomerDatabase customerDatabase;
    @DIDependency private FindCustomerByIdRequestValidator findCustomerByIdRequestValidator;

    public FindCustomerByIdResponse execute(FindCustomerByIdRequest findCustomerByIdRequest){

        List<CoreError> errors = findCustomerByIdRequestValidator.validate(findCustomerByIdRequest);

        if (!errors.isEmpty()){
            return new FindCustomerByIdResponse(errors);
        }
        Optional<Customer> expectedCustomer = customerDatabase.findById(findCustomerByIdRequest.getId());

        if (expectedCustomer.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain customer with id "
            + findCustomerByIdRequest.getId()));
            return new FindCustomerByIdResponse(errors);
        }
        return new FindCustomerByIdResponse(customerDatabase.findById(findCustomerByIdRequest.getId()));
    }
}
