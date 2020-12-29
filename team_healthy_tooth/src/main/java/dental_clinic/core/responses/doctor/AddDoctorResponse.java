package dental_clinic.core.responses.doctor;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class AddDoctorResponse extends CoreResponse {

    private Doctor doctor;

    public AddDoctorResponse(Doctor doctor) {
        this.doctor = doctor;
    }

    public AddDoctorResponse(List<CoreError> errors) {
        super(errors);
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
