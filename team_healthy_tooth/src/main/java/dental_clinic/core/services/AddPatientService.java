package dental_clinic.core.services;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.AddPatientRequest;
import dental_clinic.core.responses.AddPatientResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.services.validators.AddPatientRequestValidator;
import dental_clinic.database.PatientDatabase;
import dental_clinic.dependency_injection.DIComponent;
import dental_clinic.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class AddPatientService {

    @DIDependency private PatientDatabase patientDatabase;
    @DIDependency private AddPatientRequestValidator validator;

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