package dental_clinic.core.responses.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class ChangePersonalDataResponse extends CoreResponse {

    private Long id;

    public ChangePersonalDataResponse(List<CoreError> errors) {
        super(errors);
    }

    public ChangePersonalDataResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}