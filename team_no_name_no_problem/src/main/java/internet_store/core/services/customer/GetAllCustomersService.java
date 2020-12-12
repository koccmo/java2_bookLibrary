package internet_store.core.services.customer;

import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.GetAllCustomersResponse;
import internet_store.core.services.customer.validators.GetAllCustomersValidator;
import internet_store.database.customer.CustomerDatabase;
import internet_store.core.domain.Customer;
import internet_store.dependency_injection.DIDependency;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllCustomersService {

    @DIDependency private CustomerDatabase customerDatabase;
    @DIDependency private GetAllCustomersValidator getAllCustomersValidator;

    public GetAllCustomersResponse execute(GetAllCustomersRequest getAllCustomersRequest){
        List<CoreError> errors = getAllCustomersValidator.validate(getAllCustomersRequest);

        if (customerDatabase.getCustomers().isEmpty()){
            errors.add(new CoreError("database", "Customer database is empty"));
            return new GetAllCustomersResponse(errors, new ArrayList<>());
            }

        List<Customer> customers = customerDatabase.getCustomers();
        return new GetAllCustomersResponse(customers);
        }
}
