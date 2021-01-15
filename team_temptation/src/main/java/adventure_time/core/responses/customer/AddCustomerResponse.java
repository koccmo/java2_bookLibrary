package adventure_time.core.responses.customer;

import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;

import java.util.List;

public class AddCustomerResponse extends CoreResponse {

    public AddCustomerResponse() {}

    public AddCustomerResponse(List<CoreError> errors) {
        super(errors);
    }

}
