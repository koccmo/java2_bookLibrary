package dental_clinic.core.services.doctor;

import dental_clinic.core.requests.doctor.AddDoctorRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.doctor.AddDoctorResponse;
import dental_clinic.core.validators.doctor.AddDoctorRequestValidator;
import dental_clinic.database.DoctorDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddDoctorService {

    @Autowired
    private AddDoctorRequestValidator addDoctorRequestValidator;
    @Autowired
    private DoctorDatabase doctorDatabase;

    public AddDoctorResponse execute (AddDoctorRequest addDoctorRequest) {

        List<CoreError> errors = addDoctorRequestValidator.validate(addDoctorRequest);

        if (!errors.isEmpty()) {
            return new AddDoctorResponse(errors);
        }

        if (doctorDatabase.containsDoctor(addDoctorRequest.getDoctor())) {
            errors.add(new CoreError("database", "Database contains the same doctor"));
            return new AddDoctorResponse(errors);
        }

        return new AddDoctorResponse(addDoctorRequest.getDoctor());
    }

}
