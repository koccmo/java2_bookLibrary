package dental_clinic.core.validators.planned_visit;

import dental_clinic.core.requests.plannedVisit.CancelPlannedVisitRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CancelPlannedVisitValidator {

    public List<CoreError> validate (CancelPlannedVisitRequest cancelPlannedVisitRequest) {
        List<CoreError> errors = new ArrayList<>();

        if (cancelPlannedVisitRequest.getId() == null || cancelPlannedVisitRequest.getId() < 1) {
            errors.add(new CoreError("id", "Not valid input for id"));
        }
        return errors;
    }
}
