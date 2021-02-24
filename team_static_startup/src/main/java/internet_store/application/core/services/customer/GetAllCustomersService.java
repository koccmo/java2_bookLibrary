package internet_store.application.core.services.customer;

import internet_store.application.core.database.customer.CustomerRepository;
import internet_store.application.core.database.jpa.JpaCustomerRepository;
import internet_store.application.core.domain.Customer;
import internet_store.application.core.responses.customer.GetAllCustomersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllCustomersService {

    @Autowired
    JpaCustomerRepository ormCustomerRepository;

    public GetAllCustomersResponse execute() {
        List<Customer> customerList = ormCustomerRepository.findAll();
        return new GetAllCustomersResponse(customerList);
    }

}
