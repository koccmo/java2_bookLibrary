package adventure_time.core.services.customers;

import adventure_time.core.requests.customers.RemoveCustomerRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.customer.RemoveCustomerResponse;
import adventure_time.database.customers.DatabaseCustomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveCustomerService {

    @Autowired
    private DatabaseCustomers database;
    @Autowired private RemoveCustomerRequestValidator validator;

    public RemoveCustomerResponse remove (RemoveCustomerRequest request) {

        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new RemoveCustomerResponse(errors);
        }

        return new RemoveCustomerResponse(database.deactivate(request.getCustomerId()));
    }
}
