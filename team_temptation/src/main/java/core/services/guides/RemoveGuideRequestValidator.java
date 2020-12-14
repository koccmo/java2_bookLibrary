package core.services.guides;

import core.requests.events.RemoveEventRequest;
import core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class RemoveGuideRequestValidator {

    public List<CoreError> validate(RemoveEventRequest request) {

        // Validation
        List<CoreError> errors = new ArrayList<>();
        if (request.getEventName() == null || request.getEventName().isEmpty()) {
            // error
            CoreError error = new CoreError("eventName", "Must be not empty");
            errors.add(error);
        }
        return errors;
    }
}
