package dental_clinic.core.validators.doctor;

import dental_clinic.core.requests.doctor.DeleteDoctorRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteDoctorRequestValidator {

    public List<CoreError> validate (DeleteDoctorRequest deleteDoctorRequest) {

        List<CoreError> errors = new ArrayList<>();

        if (deleteDoctorRequest.getId() == null || deleteDoctorRequest.getId() < 1 ) {
            errors.add(new CoreError("id", "Not valid input for id"));
        }
        return errors;
    }
}
