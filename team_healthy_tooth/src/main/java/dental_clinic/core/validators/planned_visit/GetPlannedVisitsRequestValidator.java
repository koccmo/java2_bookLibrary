package dental_clinic.core.validators.planned_visit;

import dental_clinic.core.requests.plannedVisit.GetPlannedVisitsRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetPlannedVisitsRequestValidator {

    public List<CoreError> validate(GetPlannedVisitsRequest getPlannedVisitsRequest) {
        return new ArrayList<>();
    }
}
