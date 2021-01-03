package core.responses.events;

import core.responses.CoreError;
import core.responses.CoreResponse;

import java.util.List;

public class RemoveEventResponse extends CoreResponse {

    private boolean successRemoval;

    public RemoveEventResponse() {}

    public RemoveEventResponse(List<CoreError> errors) {
        super(errors);
    }


    public RemoveEventResponse(boolean successRemoval) {
        this.successRemoval = successRemoval;
    }

    public boolean isSuccessRemoval() {
        return this.successRemoval;
    }
}
