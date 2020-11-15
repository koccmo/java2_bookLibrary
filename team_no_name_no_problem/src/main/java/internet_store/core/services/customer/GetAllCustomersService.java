package internet_store.core.services.customer;

import internet_store.core.domain.Product;
import internet_store.core.requests.customer.GetCustomersRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.GetCustomersResponse;
import internet_store.database.customer.CustomerDatabase;
import internet_store.core.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class GetAllCustomersService {

    private final CustomerDatabase customerDatabase;

    public GetAllCustomersService(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public GetCustomersResponse execute(GetCustomersRequest getCustomersRequest){
        List<CoreError> errors = new ArrayList<>();

        if (customerDatabase.getCustomers().isEmpty()){
            errors.add(new CoreError("database", "Customer database is empty"));
            return new GetCustomersResponse(errors, new ArrayList<>());
            }
        List<Customer> customers = customerDatabase.getCustomers();
        return new GetCustomersResponse(customers);
        }
}
