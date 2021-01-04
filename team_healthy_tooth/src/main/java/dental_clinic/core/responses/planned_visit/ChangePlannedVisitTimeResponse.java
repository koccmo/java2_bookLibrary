package dental_clinic.core.responses.planned_visit;

import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.GregorianCalendar;
import java.util.List;

public class ChangePlannedVisitTimeResponse extends CoreResponse {

    private Long id;
    private GregorianCalendar visitTime;

    public ChangePlannedVisitTimeResponse(Long id, GregorianCalendar visitTime) {
        this.id = id;
        this.visitTime = visitTime;
    }

    public ChangePlannedVisitTimeResponse(List<CoreError> errors) {
        super(errors);
    }

    public Long getId() {
        return id;
    }

    public GregorianCalendar getVisitTime() {
        return visitTime;
    }
}
