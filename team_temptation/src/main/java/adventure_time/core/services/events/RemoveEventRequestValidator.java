package adventure_time.core.services.events;

import adventure_time.core.requests.events.RemoveEventRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.dependencies.DIComponent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@DIComponent
@Component
public class RemoveEventRequestValidator {

    public List<CoreError> validate (RemoveEventRequest request) {

        // Validation
        List<CoreError> errors = new ArrayList<>();

        // In case of deletionWay error definition
        if (request.getDeletionWay() == null || request.getDeletionWay().isBlank()) {
            CoreError error = new CoreError("deletionWay", "Must be defined");
            errors.add(error);
        } else {

            if (!request.getDeletionWay().equals("byName") && !request.getDeletionWay().equals("byId")) {
                CoreError error = new CoreError("deletionWay", "Must be \"by name\" or \"by ID-number\"");
                errors.add(error);
            }
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
