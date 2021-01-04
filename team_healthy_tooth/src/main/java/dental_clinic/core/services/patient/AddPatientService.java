package dental_clinic.core.services.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.responses.patient.AddPatientResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.patient.AddPatientRequestValidator;
import dental_clinic.database.in_memory.patient.PatientDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddPatientService {

    @Autowired
    private PatientDatabase patientDatabase;
    @Autowired private AddPatientRequestValidator validator;

    public AddPatientResponse execute (AddPatientRequest addPatientRequest){
        List<CoreError> errors = validator.validate(addPatientRequest);

        if (!errors.isEmpty()){
            return new AddPatientResponse(errors);
        }

        Patient patient = new Patient((addPatientRequest.getPersonalData()));

        if (patientDatabase.containsSpecificPersonalData(patient.getPersonalData())){
            errors.add(new CoreError("database", "Database contains the same patient"));
            return new AddPatientResponse(errors);
        }else{
            patientDatabase.addPatient(patient.getPersonalData());
            return new AddPatientResponse(patient);
        }
    }


}