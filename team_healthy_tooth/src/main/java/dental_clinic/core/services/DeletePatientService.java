package dental_clinic.core.services;

import dental_clinic.core.requests.DeletePatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.DeletePatientResponse;
import dental_clinic.core.services.validators.DeletePatientValidator;
import dental_clinic.database.PatientDatabase;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.dependency_injection.DIComponent;
import dental_clinic.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class DeletePatientService {

    @DIDependency private PatientDatabase patientDatabase;
    @DIDependency private DeletePatientValidator deletePatientValidator;

    public DeletePatientResponse execute(DeletePatientRequest deletePatientRequest){

        List<CoreError> errors = deletePatientValidator.validate(deletePatientRequest);

        if (!errors.isEmpty()){
            return new DeletePatientResponse(errors);
        }

        if (patientDatabase.containsPatientWithSpecificId(deletePatientRequest.getId())){
            for (int i = 0; i < patientDatabase.getPatients().size(); i++){
                if (getCurrentPatientPersonalData(i).getId() == deletePatientRequest.getId()){
                    patientDatabase.deletePatient(deletePatientRequest.getId());
                    return new DeletePatientResponse(deletePatientRequest.getId());
                }
            }
        }

        errors.add(new CoreError("id", "Database doesn't contain patient with id"));
        return new DeletePatientResponse(errors);
    }

    private PersonalData getCurrentPatientPersonalData(int index){
        return patientDatabase.getPatients().get(index).getPersonalData();
    }
}
