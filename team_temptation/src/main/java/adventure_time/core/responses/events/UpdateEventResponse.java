package adventure_time.core.responses.events;

import adventure_time.core.domain.Events;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;

import java.util.List;

public class UpdateEventResponse extends CoreResponse {

    private Events event;

    public UpdateEventResponse (Events event, List<CoreError> errors) {
        super(errors);
        this.event = event;
    }

    public Events getEvent() {
        return event;
    }

}
