package dental_clinic.core.services;


import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.ContainsDatabaseIdRequest;
import dental_clinic.core.responses.ContainsDatabaseIdResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.database.PatientDatabase;

import java.util.List;


public class ContainsDatabaseIdService {

    private final PatientDatabase patientDatabase;
    private ContainsDatabaseIdValidator containsDatabaseIdValidator;

    public ContainsDatabaseIdService(PatientDatabase patientDatabase, ContainsDatabaseIdValidator containsDatabaseIdValidator) {
        this.patientDatabase = patientDatabase;
        this.containsDatabaseIdValidator = containsDatabaseIdValidator;
    }

    public ContainsDatabaseIdResponse execute(ContainsDatabaseIdRequest containsDatabaseIdRequest){

        List<CoreError> errors = containsDatabaseIdValidator.validate(containsDatabaseIdRequest);

        if (!errors.isEmpty()){
            return new ContainsDatabaseIdResponse(errors);
        }

        for (int i = 0; i < patientDatabase.getPatients().size(); i++){
            if (getCurrentPatientPersonalData(i).getId() == containsDatabaseIdRequest.getId()){
                patientDatabase.containsPatientWithSpecificId(containsDatabaseIdRequest.getId());
                return new ContainsDatabaseIdResponse(containsDatabaseIdRequest.getId());
            }
        }

        errors.add(new CoreError("id = ", "Database doesn't contain patient with id"));
        return new ContainsDatabaseIdResponse(errors);
    }

    private PersonalData getCurrentPatientPersonalData(int index){
        return patientDatabase.getPatients().get(index).getPersonalData();
    }
}