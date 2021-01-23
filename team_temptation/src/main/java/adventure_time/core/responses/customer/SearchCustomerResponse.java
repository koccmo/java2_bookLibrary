package adventure_time.core.responses.customer;

import adventure_time.core.domain.Customers;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;

import java.util.List;

public class SearchCustomerResponse extends CoreResponse {

    private Customers customer;

    public SearchCustomerResponse(Customers customer, List<CoreError> errors) {
        super(errors);
        this.customer = customer;
    }

    public Customers getCustomer () {
        return customer;
    }

}
