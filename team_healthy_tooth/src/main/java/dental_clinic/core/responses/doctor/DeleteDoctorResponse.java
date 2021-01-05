package dental_clinic.core.responses.doctor;

import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class DeleteDoctorResponse extends CoreResponse {

    private Long id;

    public DeleteDoctorResponse(Long id) {
        this.id = id;
    }

    public DeleteDoctorResponse(List<CoreError> errors) {
        super(errors);
    }

    public Long getId() {
        return id;
    }
}
