package adventure_time.core.responses.events;

import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;

import java.util.List;

public class AddEventResponse extends CoreResponse {

    public AddEventResponse() {}

    public AddEventResponse(List<CoreError> errors) {
        super(errors);
    }
}
