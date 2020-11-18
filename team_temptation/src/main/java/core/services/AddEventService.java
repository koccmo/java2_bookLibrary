package core.services;

import domain.Events;
import database.Database;
import core.requests.AddEventRequest;
import core.responses.AddEventResponse;
import core.responses.CoreError;

import java.util.List;

public class AddEventService {

    private final Database database;
    private AddEventRequestValidator validator;

    public AddEventService(Database database, AddEventRequestValidator validator) {
        this.database = database;
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
        database.add(event);

        return new AddEventResponse();

    }
}
