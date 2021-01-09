package dental_clinic.core.responses.patient;

import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;
import java.util.Map;

public class UpdatePatientJowlInfoResponse extends CoreResponse {

    private Long id;
    private Map<Integer, ToothStatus> jowlInfo;

    public UpdatePatientJowlInfoResponse(Long id, Map<Integer, ToothStatus> jowlInfo) {
        this.id = id;
        this.jowlInfo = jowlInfo;
    }

    public UpdatePatientJowlInfoResponse(List<CoreError> errors) {
        super(errors);
    }

    public Long getId () {
        return id;
    }

    public Map<Integer, ToothStatus> getJowlInfo() {
        return jowlInfo;
    }
}
