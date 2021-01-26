package adventure_time.core.responses.customer;

import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;

import java.util.List;

public class UpdateCustomerResponse extends CoreResponse {

    public UpdateCustomerResponse () {};

    public UpdateCustomerResponse (List<CoreError> errors) {
        super(errors);
    }
}
