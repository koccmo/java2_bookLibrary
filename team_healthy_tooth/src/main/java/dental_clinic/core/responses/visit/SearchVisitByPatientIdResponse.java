package dental_clinic.core.responses.visit;

import dental_clinic.core.domain.Visit;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class SearchVisitByPatientIdResponse extends CoreResponse {

    private List<Visit> visitList;

    public SearchVisitByPatientIdResponse(List<Visit> visitList) {
        this.visitList = visitList;
    }

    public SearchVisitByPatientIdResponse(List<CoreError> errors, List<Visit> visitList) {
        super(errors);
    }

    public List<Visit> getVisitList() {
        return visitList;
    }
}
