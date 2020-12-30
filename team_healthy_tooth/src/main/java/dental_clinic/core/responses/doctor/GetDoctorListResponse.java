package dental_clinic.core.responses.doctor;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class GetDoctorListResponse extends CoreResponse {

    private List<Doctor> doctors;

    public GetDoctorListResponse(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public GetDoctorListResponse(List<CoreError> errors, List<Doctor> doctors) {
        super(errors);
        this.doctors = doctors;
    }

    public List<Doctor>getDoctors() {
        return doctors;
    }
}
