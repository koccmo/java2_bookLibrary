package dental_clinic.core.responses.planned_visit;

import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class GetPlannedVisitsResponse extends CoreResponse {

    private List <PlannedVisit> plannedVisits;

    public GetPlannedVisitsResponse(List <PlannedVisit> plannedVisits) {
        this.plannedVisits = plannedVisits;
    }

    public GetPlannedVisitsResponse(List<CoreError> errors, List <PlannedVisit> plannedVisits) {
        super(errors);
    }

    public List<PlannedVisit> getPlannedVisits() {
        return plannedVisits;
    }
}
