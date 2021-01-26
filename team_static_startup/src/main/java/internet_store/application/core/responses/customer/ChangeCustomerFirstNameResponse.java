package internet_store.application.core.responses.customer;

import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class ChangeCustomerFirstNameResponse extends CoreResponse {

    private boolean nameChanged;

    public ChangeCustomerFirstNameResponse(boolean nameChanged) {
        this.nameChanged = nameChanged;
    }

    public ChangeCustomerFirstNameResponse(List<CoreError> errors, boolean nameChanged) {
        super(errors);
        this.nameChanged = nameChanged;
    }

    public boolean isNameChanged() {
        return nameChanged;
    }

}
