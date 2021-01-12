package adventure_time.core.responses.customer;

import adventure_time.core.domain.Customers;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;

import java.util.List;

public class SearchCustomerResponse extends CoreResponse {

    private List<Customers> customers;

    public SearchCustomerResponse(List<Customers> customers, List<CoreError> errors) {
        super(errors);
        this.customers = customers;
    }

    public List<Customers> getCustomers() {
        return customers;
    }

}
