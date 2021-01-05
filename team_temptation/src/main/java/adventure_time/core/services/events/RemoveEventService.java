package adventure_time.core.services.events;

import adventure_time.core.requests.events.RemoveEventRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.events.RemoveEventResponse;
import adventure_time.database.events.EventDatabase;
import adventure_time.dependencies.DIComponent;
import adventure_time.dependencies.DIDependency;

import java.util.List;

@DIComponent
public class RemoveEventService {
    @DIDependency
    private EventDatabase databaseEvents;
    @DIDependency private RemoveEventRequestValidator validator;

//    public RemoveEventService(EventDatabase databaseEvents, RemoveEventRequestValidator validator) {
//        this.databaseEvents = databaseEvents;
//        this.validator = validator;
//    }

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
