package dental_clinic.core.responses.patient;

import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;
import java.util.Map;

public class UpdatePatientJowlInfoResponse extends CoreResponse {

    private Long id;

    public UpdatePatientJowlInfoResponse(Long id) {
        this.id = id;
    }

    public UpdatePatientJowlInfoResponse(List<CoreError> errors) {
        super(errors);
    }

    public Long getId () {
        return id;
    }
}
