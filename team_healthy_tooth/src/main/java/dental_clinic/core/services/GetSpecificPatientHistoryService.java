package dental_clinic.core.services;

import dental_clinic.core.requests.GetSpecificPatientHistoryRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.GetSpecificPatientHistoryResponse;
import dental_clinic.database.PatientDatabase;
import dental_clinic.core.domain.Patient;

import java.util.List;
import java.util.Optional;

public class GetSpecificPatientHistoryService {

    private final PatientDatabase patientDatabase;
    private GetSpecificPatientValidator getSpecificPatientValidator;

    public GetSpecificPatientHistoryService(PatientDatabase patientDatabase, GetSpecificPatientValidator getSpecificPatientValidator) {
        this.patientDatabase = patientDatabase;
        this.getSpecificPatientValidator = getSpecificPatientValidator;
    }

    public GetSpecificPatientHistoryResponse execute(GetSpecificPatientHistoryRequest getSpecificPatientHistoryRequest){

        List<CoreError> errors = getSpecificPatientValidator.validate(getSpecificPatientHistoryRequest);

        if (!errors.isEmpty()){
            return new GetSpecificPatientHistoryResponse(errors);
        }

        for (int i = 0; i < patientDatabase.getPatients().size(); i++){
            if (isSpecificPatient(i, getSpecificPatientHistoryRequest.getId())){
                return new GetSpecificPatientHistoryResponse(Optional.of(patientDatabase.getPatients().get(i)));
            }
        }

        errors.add(new CoreError("id", "Database doesn't contain patient with id"));
        return new GetSpecificPatientHistoryResponse(errors);
    }

    private boolean isSpecificPatient (int index, long id) {
        return patientDatabase.getPatients().get(index).getPersonalData().getId() == id;
    }
}
