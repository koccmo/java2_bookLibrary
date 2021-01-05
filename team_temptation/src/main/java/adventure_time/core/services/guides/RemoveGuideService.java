package adventure_time.core.services.guides;

import adventure_time.core.requests.events.RemoveEventRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.events.RemoveEventResponse;
import adventure_time.database.events.EventDatabase;

import java.util.List;

public class RemoveGuideService {
    private final EventDatabase databaseEvents;
    private RemoveGuideRequestValidator validator;

    public RemoveGuideService(EventDatabase databaseEvents, RemoveGuideRequestValidator validator) {
        this.databaseEvents = databaseEvents;
        this.validator = validator;
    }


//    public RemoveEventResponse removeEvent(RemoveEventRequest request) {
//
//        List<CoreError> errors = validator.validate(request);
//
//        if (!errors.isEmpty()) {
//            return new RemoveEventResponse(errors);
//        }
//
//        if (databaseEvents.removeByName(request.getEventName())) return new RemoveEventResponse();
//
//        errors.add(new CoreError("eventName", "An event \"" + request.getEventName() + "\" was not found."));
//        return new RemoveEventResponse(errors);
//    }
}
