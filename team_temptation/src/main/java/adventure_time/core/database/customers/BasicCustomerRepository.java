package adventure_time.core.database.customers;

import adventure_time.core.domain.Customers;

import java.util.List;
import java.util.Optional;


public interface BasicCustomerRepository {

    boolean add(Customers customer);

}
