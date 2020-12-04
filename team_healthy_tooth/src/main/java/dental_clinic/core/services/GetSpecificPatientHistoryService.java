package dental_clinic.core.services;

import dental_clinic.core.requests.GetSpecificPatientHistoryRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.GetSpecificPatientHistoryResponse;
import dental_clinic.core.services.validators.GetSpecificPatientHistoryRequestValidator;
import dental_clinic.database.PatientDatabase;
import dental_clinic.dependency_injection.DIComponent;
import dental_clinic.dependency_injection.DIDependency;

import java.util.List;
import java.util.Optional;

@DIComponent
public class GetSpecificPatientHistoryService {

    @DIDependency private final PatientDatabase patientDatabase;
    @DIDependency private GetSpecificPatientHistoryRequestValidator getSpecificPatientHistoryRequestValidator;

    public GetSpecificPatientHistoryService(PatientDatabase patientDatabase, GetSpecificPatientHistoryRequestValidator getSpecificPatientHistoryRequestValidator) {
        this.patientDatabase = patientDatabase;
        this.getSpecificPatientHistoryRequestValidator = getSpecificPatientHistoryRequestValidator;
    }

    public GetSpecificPatientHistoryResponse execute(GetSpecificPatientHistoryRequest getSpecificPatientHistoryRequest){

        List<CoreError> errors = getSpecificPatientHistoryRequestValidator.validate(getSpecificPatientHistoryRequest);

        if (!errors.isEmpty()){
            return new GetSpecificPatientHistoryResponse(errors);
        }

        if (patientDatabase.containsPatientWithSpecificId(getSpecificPatientHistoryRequest.getId())){
            for (int i = 0; i < patientDatabase.getPatients().size(); i++){
                if (isSpecificPatient(i, getSpecificPatientHistoryRequest.getId())){
                    return new GetSpecificPatientHistoryResponse(Optional.of(patientDatabase.getPatients().get(i)));
                }
            }
        }

        errors.add(new CoreError("id", "Database doesn't contain patient with id"));
        return new GetSpecificPatientHistoryResponse(errors);
    }

    private boolean isSpecificPatient (int index, Long id) {
        return patientDatabase.getPatients().get(index).getPersonalData().getId().equals(id);
    }
}
