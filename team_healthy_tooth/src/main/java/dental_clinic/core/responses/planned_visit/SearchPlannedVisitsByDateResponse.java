package dental_clinic.core.responses.planned_visit;

import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class SearchPlannedVisitsByDateResponse extends CoreResponse {

    private List<PlannedVisit> plannedVisitList;

    public SearchPlannedVisitsByDateResponse(List<PlannedVisit> plannedVisitList) {
        this.plannedVisitList = plannedVisitList;
    }

    public SearchPlannedVisitsByDateResponse(List<CoreError> errors, List<PlannedVisit> plannedVisitList) {
        super(errors);
    }

    public List<PlannedVisit> getPlannedVisitList() {
        return plannedVisitList;
    }
}
