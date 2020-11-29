package dental_clinic.core.services;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.AddPatientRequest;
import dental_clinic.core.responses.AddPatientResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.database.PatientDatabase;

import java.util.List;

public class AddPatientService {

    private PatientDatabase patientDatabase;

    private AddPatientRequestValidator validator;

    public AddPatientService(PatientDatabase patientDatabase, AddPatientRequestValidator addPatientRequestValidator) {
        this.patientDatabase = patientDatabase;
        this.validator = addPatientRequestValidator;
    }

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