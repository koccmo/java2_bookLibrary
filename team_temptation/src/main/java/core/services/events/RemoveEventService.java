package core.services.events;

import core.requests.events.RemoveEventRequest;
import core.responses.CoreError;
import core.responses.events.RemoveEventResponse;
import database.events.EventDatabase;

import java.util.List;

public class RemoveEventService {
    private final EventDatabase databaseEvents;
    private RemoveEventRequestValidator validator;

    public RemoveEventService(EventDatabase databaseEvents, RemoveEventRequestValidator validator) {
        this.databaseEvents = databaseEvents;
        this.validator = validator;
    }

    public RemoveEventResponse removeEvent(RemoveEventRequest request) {

        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new RemoveEventResponse(errors);
        }

        boolean isSuccessRemoval;
        if (request.getDeletionWay().equals("byName")) {
            isSuccessRemoval = databaseEvents.removeByName(request.getEventName());
        } else {
            isSuccessRemoval = databaseEvents.removeById(request.getEventId());
        }

        return new RemoveEventResponse(isSuccessRemoval);

    }
}
