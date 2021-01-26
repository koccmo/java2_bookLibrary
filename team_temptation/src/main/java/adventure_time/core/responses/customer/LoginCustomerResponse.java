package adventure_time.core.responses.customer;

import adventure_time.core.domain.Customers;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;

import java.util.List;

public class LoginCustomerResponse extends CoreResponse {

        public LoginCustomerResponse (Long id) { super (id);}

        public LoginCustomerResponse (Customers customer) { super (customer);}

        public LoginCustomerResponse (List<CoreError> errors) {
            super(errors);
        }
}
