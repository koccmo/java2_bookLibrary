package core.responses.events;

import core.responses.CoreError;
import core.responses.CoreResponse;
import domain.Events;

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
