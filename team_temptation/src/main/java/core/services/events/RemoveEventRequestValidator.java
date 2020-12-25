package core.services.events;

import core.requests.events.RemoveEventRequest;
import core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class RemoveEventRequestValidator {

    public List<CoreError> validate (RemoveEventRequest request) {

        // Validation
        List<CoreError> errors = new ArrayList<>();

        if (request.getDeletionWay() == null || request.getDeletionWay().isBlank()) {
            // error
            CoreError error = new CoreError("deletionWay", "Must be defined");
            errors.add(error);
        }

        if ((!request.getDeletionWay().equals("byName") && !request.getDeletionWay().equals("byId"))
                && !request.getDeletionWay().isBlank()) {
            // error
            CoreError error = new CoreError("deletionWay", "Must be \"by name\" or \"by ID-number\"");
            errors.add(error);
        }

        if (request.getDeletionWay().equals("byName")
                && (request.getEventName().isBlank() || request.getEventName() == null)) {
            // error
            CoreError error = new CoreError("eventName", "Must be defined");
            errors.add(error);
        }

        if (request.getDeletionWay().equals("byId") && request.getEventId() == null) {
            // error
            CoreError error = new CoreError("eventId", "Must be defined");
            errors.add(error);
        }

        if (request.getDeletionWay().equals("byId") && request.getEventId() <= 0 ) {
            // error
            CoreError error = new CoreError("eventId", "Must be above zero");
            errors.add(error);
        }



        return errors;
    }
}
