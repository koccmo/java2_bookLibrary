package dental_clinic.core.services;

import dental_clinic.core.requests.FindPatientByPersonalCodeRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.FindPatientByPersonalCodeResponse;
import dental_clinic.database.PatientDatabase;
import dental_clinic.core.domain.Patient;

import java.util.List;
import java.util.Optional;

public class FindPatientsByPersonalCodeService {

    private final PatientDatabase patientDatabase;
    private final FindPatientByPersonalCodeValidator findPatientByPersonalCodeValidator;

    public FindPatientsByPersonalCodeService(PatientDatabase patientDatabase, FindPatientByPersonalCodeValidator findPatientByPersonalCodeValidator) {
        this.patientDatabase = patientDatabase;
        this.findPatientByPersonalCodeValidator = findPatientByPersonalCodeValidator;
    }

    public FindPatientByPersonalCodeResponse execute(FindPatientByPersonalCodeRequest findPatientByPersonalCodeRequest){
        List <CoreError> errors = findPatientByPersonalCodeValidator.validate(findPatientByPersonalCodeRequest);

        if (!errors.isEmpty()){
            return new FindPatientByPersonalCodeResponse(errors);
        }

        Optional<Patient> specificPatient = patientDatabase.findPatientsByPersonalCode(findPatientByPersonalCodeRequest.getPersonalCodeToSearch());

        if (specificPatient.isPresent()){
            return new FindPatientByPersonalCodeResponse(specificPatient.get());
        }

        errors.add(new CoreError("database", "Database doesn't contain patient with personal code " +
                findPatientByPersonalCodeRequest.getPersonalCodeToSearch()));
        return new FindPatientByPersonalCodeResponse(errors);
    }
}
