package core.responses;

import java.util.List;

public class AddEventResponse extends CoreResponse {

    public AddEventResponse() {}

    public AddEventResponse(List<CoreError> errors) {
        super(errors);
    }
}
