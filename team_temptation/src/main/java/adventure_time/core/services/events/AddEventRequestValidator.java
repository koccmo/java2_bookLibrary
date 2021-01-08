
package adventure_time.core.services.events;

import adventure_time.core.requests.events.AddEventRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.dependencies.DIComponent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@DIComponent
@Component
public class AddEventRequestValidator {

    private static final int MIN_ROUTE = 3;
    private static final int MIN_EVENT_NAME = 3;
    private static final int MIN_DESCRIPTION = 3;


    public List<CoreError> validate (AddEventRequest request) {

        // Validation
        List<CoreError> errors = new ArrayList<>();
        if (request.getEventName() == null || request.getEventName().isEmpty()) {
            // error
            CoreError error = new CoreError("eventName", "Must be not empty");
            errors.add(error);
        }
        if (!(request.getEventName() == null || request.getEventName().isEmpty()) && request.getEventName().length() <= MIN_EVENT_NAME) {
            // error
            CoreError error = new CoreError("eventName", "Must contain more than 3 symbols");
            errors.add(error);
        }
        if (request.getEventKind() == null || request.getEventKind().isEmpty()) {
            // error
            CoreError error = new CoreError("eventKind", "Must be not empty");
            errors.add(error);
        } else {
            if (!(request.getEventKind().equals("bike trip") || request.getEventKind().equals("boat trip")
                    || request.getEventKind().equals("walking trip") || request.getEventKind().equals("motorcycle trip")
                    || request.getEventKind().equals("bus trip"))) {
                // error
                CoreError error = new CoreError("eventKind", "Incorrect");
                errors.add(error);
            }
        }
        if (request.getDurationHours() <= 0) {
            // error
            CoreError error = new CoreError("durationHours", "Must be bigger than 0");
            errors.add(error);
        }
        if (request.getMaxNumberParticipants() <= 0) {
            // error
            CoreError error = new CoreError("maxNumberParticipants", "Must be bigger than 0");
            errors.add(error);
        }
        if (request.getMinNumberParticipants() <= 0) {
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
            CoreError error = new CoreError("route", "Must be not empty");
            errors.add(error);
        }
        if (!(request.getRoute() == null || request.getRoute().isEmpty()) && request.getRoute().length() <= MIN_ROUTE) {
            // error
            CoreError error = new CoreError("route", "Must contain more than 3 symbols");
            errors.add(error);
        }
        if (request.getDetailsDescription() == null || request.getDetailsDescription().isEmpty()) {
            // error
            CoreError error = new CoreError("detailDescription", "Must be not empty");
            errors.add(error);
        }
        if (!(request.getDetailsDescription() == null || request.getDetailsDescription().isEmpty()) && request.getDetailsDescription().length() <= MIN_DESCRIPTION) {
            // error
            CoreError error = new CoreError("detailDescription", "Must contain more than 3 symbols");
            errors.add(error);
        }
        return errors;
    }
}
