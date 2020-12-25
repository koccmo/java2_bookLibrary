package core.responses.events;

import core.responses.CoreError;
import core.responses.CoreResponse;

import java.util.List;

public class RemoveEventResponse extends CoreResponse {

    public RemoveEventResponse() {}

    public RemoveEventResponse(List<CoreError> errors) {
        super(errors);
    }
}
