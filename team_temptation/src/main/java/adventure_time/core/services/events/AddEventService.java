package adventure_time.core.services.events;

import adventure_time.core.requests.events.AddEventRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.events.AddEventResponse;
import adventure_time.database.events.EventDatabase;
//import adventure_time.dependencies.DIComponent;
//import adventure_time.dependencies.DIDependency;
import adventure_time.core.domain.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@DIComponent
@Component
public class AddEventService {

//    @DIDependency
    @Autowired
    private EventDatabase databaseEvents;
//    @DIDependency
    @Autowired private AddEventRequestValidator validator;

//    public AddEventService(EventDatabase databaseEvents, AddEventRequestValidator validator) {
//        this.databaseEvents = databaseEvents;
//        this.validator = validator;
//    }

    public AddEventResponse addEvent (AddEventRequest request) {
        // Validation
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new AddEventResponse(errors);
        }

        Events event = new Events(request.getEventName(), request.getEventKind(),
                request.getDurationHours(), request.getMaxNumberParticipants(),
                request.getMinNumberParticipants(), request.getRoute(),
                request.getDetailsDescription());

        if (databaseEvents.add(event)) return new AddEventResponse();

        errors.add(new CoreError("eventName", "The event \"" + request.getEventName() + "\" already exists"));
        return new AddEventResponse(errors);

    }
}
