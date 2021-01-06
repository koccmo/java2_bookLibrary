package adventure_time.core.services.guides;

import adventure_time.core.requests.events.AddEventRequest;
import adventure_time.core.responses.events.AddEventResponse;
import adventure_time.core.responses.CoreError;
import adventure_time.database.events.EventDatabase;
import adventure_time.domain.Events;

import java.util.List;

public class AddGuideService {

    private final EventDatabase databaseEvents;
    private AddGuideRequestValidator validator;

    public AddGuideService(EventDatabase databaseEvents, AddGuideRequestValidator validator) {
        this.databaseEvents = databaseEvents;
        this.validator = validator;
    }

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
