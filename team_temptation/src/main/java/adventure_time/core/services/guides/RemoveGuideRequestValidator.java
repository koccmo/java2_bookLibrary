package adventure_time.core.services.guides;

import adventure_time.core.requests.guides.RemoveGuideRequest;
import adventure_time.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class RemoveGuideRequestValidator {

    public List<CoreError> validate(RemoveGuideRequest request) {

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
