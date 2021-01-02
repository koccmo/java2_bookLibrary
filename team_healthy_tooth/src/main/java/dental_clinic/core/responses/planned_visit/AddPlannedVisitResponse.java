package dental_clinic.core.responses.planned_visit;

import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class AddPlannedVisitResponse extends CoreResponse {

    private PlannedVisit plannedVisit;

    public AddPlannedVisitResponse(PlannedVisit plannedVisit) {
        this.plannedVisit = plannedVisit;
    }

    public AddPlannedVisitResponse(List<CoreError> errors) {
        super(errors);
    }

    public PlannedVisit getPlannedVisit() {
        return plannedVisit;
    }
}
