package dental_clinic.core.services.patient;

import dental_clinic.core.requests.patient.DeletePatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.DeletePatientResponse;
import dental_clinic.core.validators.patient.DeletePatientValidator;
import dental_clinic.core.database.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeletePatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DeletePatientValidator deletePatientValidator;

    public DeletePatientResponse execute(DeletePatientRequest deletePatientRequest){

        List<CoreError> errors = deletePatientValidator.validate(deletePatientRequest);

        if (!errors.isEmpty()){
            return new DeletePatientResponse(errors);
        }

        if (patientRepository.containsPatientWithSpecificId(deletePatientRequest.getId())){
            patientRepository.deletePatient(deletePatientRequest.getId());
            return new DeletePatientResponse(deletePatientRequest.getId());
        }

        errors.add(new CoreError("id", "Database doesn't contain patient with id"));
        return new DeletePatientResponse(errors);
    }
}
