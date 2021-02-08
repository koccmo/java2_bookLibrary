package dental_clinic.core.responses.doctor;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.DoctorAndGraphic;
import dental_clinic.core.domain.DoctorsWorkGraphic;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class GetDoctorListResponse extends CoreResponse {

    private List<DoctorAndGraphic> list;

    public GetDoctorListResponse(List<DoctorAndGraphic> list) {
        this.list = list;
    }

    public GetDoctorListResponse(List<CoreError> errors, List<Doctor> doctors) {
        super(errors);
    }

    public List<DoctorAndGraphic> getDoctorAndGraphic() {
        return list;
    }
}
