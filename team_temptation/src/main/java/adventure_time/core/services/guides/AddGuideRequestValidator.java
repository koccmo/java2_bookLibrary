package adventure_time.core.services.guides;

import adventure_time.core.requests.events.AddEventRequest;
import adventure_time.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class AddGuideRequestValidator {

    public List<CoreError> validate (AddEventRequest request) {

        // Validation
        List<CoreError> errors = new ArrayList<>();
        if (request.getEventName() == null || request.getEventName().isEmpty()) {
            // error
            CoreError error = new CoreError("eventName", "Must be not empty");
            errors.add(error);
        }
        if (request.getEventKind() == null || request.getEventKind().isEmpty()) {
            // error
            CoreError error = new CoreError("eventKind", "Must be not empty");
            errors.add(error);
        }
        if (request.getDurationHours() == 0) {
            // error
            CoreError error = new CoreError("durationHours", "Must be bigger than 0");
            errors.add(error);
        }
        if (request.getMaxNumberParticipants() == 0) {
            // error
            CoreError error = new CoreError("maxNumberParticipants", "Must be bigger than 0");
            errors.add(error);
        }
        if (request.getMinNumberParticipants() == 0) {
            // error
            CoreError error = new CoreError("minNumberParticipants", "Must be bigger than 0");
            errors.add(error);
        }
        if (request.getMinNumberParticipants() > request.getMaxNumberParticipants()) {
            // error
            CoreError error = new CoreError("minNumberParticipants", "Must not be bigger than maxNumber");
            errors.add(error);
        }
        if (request.getRoute() == null || request.getRoute().isEmpty()) {
            // error
            CoreError error = new CoreError("detailDescription", "Must be not empty");
            errors.add(error);
        }
        if (request.getDetailsDescription() == null || request.getDetailsDescription().isEmpty()) {
            // error
            CoreError error = new CoreError("detailDescription", "Must be not empty");
            errors.add(error);
        }
        return errors;
    }
}
