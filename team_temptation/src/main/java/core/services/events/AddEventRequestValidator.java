
package core.services.events;

import core.requests.events.AddEventRequest;
import core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class AddEventRequestValidator {

    public List<CoreError> validate (AddEventRequest request) {

        // Validation
        List<CoreError> errors = new ArrayList<>();
        if (request.getEventName() == null || request.getEventName().isEmpty()) {
            // error
            CoreError error = new CoreError("eventName", "Must be not empty");
            errors.add(error);
        }
        if (!(request.getEventName() == null || request.getEventName().isEmpty()) && request.getEventName().length() <= 3) {
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
        if (!(request.getRoute() == null || request.getRoute().isEmpty()) && request.getRoute().length() <= 3) {
            // error
            CoreError error = new CoreError("route", "Must contain more than 3 symbols");
            errors.add(error);
        }
        if (request.getDetailsDescription() == null || request.getDetailsDescription().isEmpty()) {
            // error
            CoreError error = new CoreError("detailDescription", "Must be not empty");
            errors.add(error);
        }
        if (!(request.getDetailsDescription() == null || request.getDetailsDescription().isEmpty()) && request.getDetailsDescription().length() <= 3) {
            // error
            CoreError error = new CoreError("detailDescription", "Must contain more than 3 symbols");
            errors.add(error);
        }
        return errors;
    }
}
