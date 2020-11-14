package dental_clinic.core.services;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.AddPatientRequest;
import dental_clinic.core.responses.AddPatientResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.database.PatientDatabase;
import dental_clinic.core.domain.PersonalData;

import java.util.List;
import java.util.Optional;

public class AddPatientService {

    private PatientDatabase patientDatabase;

    private AddPatientValidator validator;

    public AddPatientService(PatientDatabase patientDatabase, AddPatientValidator addPatientValidator) {
        this.patientDatabase = patientDatabase;
        this.validator = addPatientValidator;
    }

    public AddPatientResponse execute (AddPatientRequest addPatientRequest){
        List<CoreError> errors = validator.validate(addPatientRequest);

        if (!errors.isEmpty()){
            return new AddPatientResponse(errors);
        }

        Patient patient = new Patient((addPatientRequest.getPersonalData()));

        if (containsDatabasePatientPersonalData(patient.getPersonalData())){
            errors.add(new CoreError("database", "Database contains the same patient"));
            return new AddPatientResponse(errors);
        }else{
            patientDatabase.addPatient(patient.getPersonalData());
            return new AddPatientResponse(patient);
        }
    }


    private boolean containsDatabasePatientPersonalData(PersonalData personalData){
        Optional<PersonalData> result = patientDatabase.getPatients().stream()
                .map(patient -> patient.getPersonalData())
                .filter(patient1 -> patient1.equals(personalData))
                .findAny();
        return result.isPresent();
    }
}