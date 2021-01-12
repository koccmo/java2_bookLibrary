package adventure_time.core.responses.customer;

import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;

import java.util.List;

public class RemoveCustomerResponse extends CoreResponse {

    private boolean successRemoval;

    public RemoveCustomerResponse() {}

    public RemoveCustomerResponse(List<CoreError> errors) {
        super(errors);
    }


    public RemoveCustomerResponse(boolean successRemoval) {
        this.successRemoval = successRemoval;
    }

    public boolean isSuccessRemoval() {
        return this.successRemoval;
    }

}
