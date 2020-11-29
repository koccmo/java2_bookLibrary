package dental_clinic.core.services;

import dental_clinic.core.requests.SearchPatientByPersonalCodeRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.SearchPatientByPersonalCodeResponse;
import dental_clinic.database.PatientDatabase;
import dental_clinic.core.domain.Patient;

import java.util.List;
import java.util.Optional;

public class SearchPatientsByPersonalCodeService {

    private final PatientDatabase patientDatabase;
    private final SearchPatientByPersonalCodeRequestValidator searchPatientByPersonalCodeRequestValidator;

    public SearchPatientsByPersonalCodeService(PatientDatabase patientDatabase, SearchPatientByPersonalCodeRequestValidator searchPatientByPersonalCodeRequestValidator) {
        this.patientDatabase = patientDatabase;
        this.searchPatientByPersonalCodeRequestValidator = searchPatientByPersonalCodeRequestValidator;
    }

    public SearchPatientByPersonalCodeResponse execute(SearchPatientByPersonalCodeRequest searchPatientByPersonalCodeRequest){
        List <CoreError> errors = searchPatientByPersonalCodeRequestValidator.validate(searchPatientByPersonalCodeRequest);

        if (!errors.isEmpty()){
            return new SearchPatientByPersonalCodeResponse(errors);
        }

        Optional<Patient> specificPatient = patientDatabase.findPatientsByPersonalCode(searchPatientByPersonalCodeRequest.getPersonalCodeToSearch());

        if (specificPatient.isPresent()){
            return new SearchPatientByPersonalCodeResponse(specificPatient.get());
        }

        errors.add(new CoreError("database", "Database doesn't contain patient with personal code " +
                searchPatientByPersonalCodeRequest.getPersonalCodeToSearch()));
        return new SearchPatientByPersonalCodeResponse(errors);
    }
}
