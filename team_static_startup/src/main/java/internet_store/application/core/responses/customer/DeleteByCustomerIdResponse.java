package internet_store.application.core.responses.customer;

import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class DeleteByCustomerIdResponse extends CoreResponse {

    private boolean customerRemoved;

    public DeleteByCustomerIdResponse(boolean customerRemoved) {
        this.customerRemoved = customerRemoved;
    }

    public DeleteByCustomerIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isCustomerRemoved() {
        return customerRemoved;
    }

}
