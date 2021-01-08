package dental_clinic.core.validators.doctor;

import dental_clinic.core.requests.doctor.GetDoctorListRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetDoctorRequestListValidator {

    public List<CoreError> validate (GetDoctorListRequest getDoctorListRequest) {
        return new ArrayList<>();
    }
}
