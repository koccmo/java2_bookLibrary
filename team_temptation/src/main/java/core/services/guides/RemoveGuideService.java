package core.services.guides;

import core.requests.events.RemoveEventRequest;
import core.responses.CoreError;
import core.responses.events.RemoveEventResponse;
import database.events.EventDatabase;

import java.util.List;

public class RemoveGuideService {
    private final EventDatabase databaseEvents;
    private RemoveGuideRequestValidator validator;

    public RemoveGuideService(EventDatabase databaseEvents, RemoveGuideRequestValidator validator) {
        this.databaseEvents = databaseEvents;
        this.validator = validator;
    }


    public RemoveEventResponse removeEvent(RemoveEventRequest request) {

        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new RemoveEventResponse(errors);
        }

        if (databaseEvents.removeByName(request.getEventName())) return new RemoveEventResponse();

        errors.add(new CoreError("eventName", "An event \"" + request.getEventName() + "\" was not found."));
        return new RemoveEventResponse(errors);
    }
}
