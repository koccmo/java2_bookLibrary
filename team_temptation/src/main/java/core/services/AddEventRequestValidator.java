package core.services;

import core.requests.AddEventRequest;
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
        if (request.getStartDate() == null || request.getStartDate().isEmpty()) {
            // error
            CoreError error = new CoreError("startDate", "Must be not empty");
            errors.add(error);
        }
        if (request.getFinishDate() == null || request.getFinishDate().isEmpty()) {
            // error
            CoreError error = new CoreError("finishDate", "Must be not empty");
            errors.add(error);
        }
        if (request.getDetailDescription() == null || request.getDetailDescription().isEmpty()) {
            // error
            CoreError error = new CoreError("detailDescription", "Must be not empty");
            errors.add(error);
        }
        return errors;
    }
}
