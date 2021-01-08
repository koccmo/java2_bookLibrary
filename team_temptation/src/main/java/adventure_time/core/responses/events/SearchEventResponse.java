package adventure_time.core.responses.events;

import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;
import adventure_time.core.domain.Events;

import java.util.List;

public class SearchEventResponse extends CoreResponse {

    private List<Events> events;

    public SearchEventResponse(List<Events> events, List<CoreError> errors) {
        super(errors);
        this.events = events;
    }

    public List<Events> getEvents() {
        return events;
    }
}
