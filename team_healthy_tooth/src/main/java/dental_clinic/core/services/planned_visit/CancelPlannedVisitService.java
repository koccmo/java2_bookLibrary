package dental_clinic.core.services.planned_visit;

import dental_clinic.core.requests.plannedVisit.CancelPlannedVisitRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.CancelPlannedVisitResponse;
import dental_clinic.core.validators.planned_visit.CancelPlannedVisitValidator;
import dental_clinic.database.in_memory.planned_visit.PlannedVisitsInMemoryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CancelPlannedVisitService {

    @Autowired
    private PlannedVisitsInMemoryDatabase plannedVisitsInMemoryDatabase;
    @Autowired
    private CancelPlannedVisitValidator cancelPlannedVisitValidator;

    public CancelPlannedVisitResponse execute (CancelPlannedVisitRequest cancelPlannedVisitRequest) {
        List<CoreError> errorList = cancelPlannedVisitValidator.validate(cancelPlannedVisitRequest);

        if (!errorList.isEmpty()) {
            return new CancelPlannedVisitResponse(errorList);
        }

        if (!plannedVisitsInMemoryDatabase.containsId(cancelPlannedVisitRequest.getId())) {
            errorList.add(new CoreError("database",
                    "Database doesn't contain id " + cancelPlannedVisitRequest.getId()));
            return new CancelPlannedVisitResponse(errorList);
        }

        return new CancelPlannedVisitResponse(cancelPlannedVisitRequest.getId());
    }
}
