package core.responses.events;

import core.responses.CoreError;
import core.responses.CoreResponse;

import java.util.List;

public class AddEventResponse extends CoreResponse {

    public AddEventResponse() {}

    public AddEventResponse(List<CoreError> errors) {
        super(errors);
    }
}
