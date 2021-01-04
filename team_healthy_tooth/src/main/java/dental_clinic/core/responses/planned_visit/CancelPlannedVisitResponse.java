package dental_clinic.core.responses.planned_visit;

import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class CancelPlannedVisitResponse extends CoreResponse {

    private Long id;

    public CancelPlannedVisitResponse(Long id) {
        this.id = id;
    }

    public CancelPlannedVisitResponse(List<CoreError> errors) {
        super(errors);
    }

    public Long getId() {
        return id;
    }
}
