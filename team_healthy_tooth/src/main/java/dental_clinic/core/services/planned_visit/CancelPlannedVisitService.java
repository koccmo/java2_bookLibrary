package dental_clinic.core.services.planned_visit;

import dental_clinic.core.requests.plannedVisit.CancelPlannedVisitRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.CancelPlannedVisitResponse;
import dental_clinic.core.validators.planned_visit.CancelPlannedVisitValidator;
import dental_clinic.core.database.planned_visit.PlannedVisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CancelPlannedVisitService {

    @Autowired
    private PlannedVisitsRepository plannedVisitsRepository;
    @Autowired
    private CancelPlannedVisitValidator cancelPlannedVisitValidator;

    public CancelPlannedVisitResponse execute (CancelPlannedVisitRequest cancelPlannedVisitRequest) {

        List<CoreError> errorList = cancelPlannedVisitValidator.validate(cancelPlannedVisitRequest);

        if (!errorList.isEmpty()) {
            return new CancelPlannedVisitResponse(errorList);
        }

        if (plannedVisitsRepository.containsId(cancelPlannedVisitRequest.getId())) {
            plannedVisitsRepository.cancelPlannedVisit(cancelPlannedVisitRequest.getId());
            return new CancelPlannedVisitResponse(cancelPlannedVisitRequest.getId());
        }

        errorList.add(new CoreError("database", "Database doesn't contain id " + cancelPlannedVisitRequest.getId()));
        return new CancelPlannedVisitResponse(errorList);
    }
}
