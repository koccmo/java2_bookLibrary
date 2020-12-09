package core.services.guides;

import core.requests.events.AddEventRequest;
import core.responses.events.AddEventResponse;
import core.responses.CoreError;
import database.events.EventDatabase;
import domain.Events;

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
