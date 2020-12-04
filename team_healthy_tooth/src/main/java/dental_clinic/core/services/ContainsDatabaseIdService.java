package dental_clinic.core.services;


import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.ContainsDatabaseIdRequest;
import dental_clinic.core.responses.ContainsDatabaseIdResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.services.validators.ContainsDatabaseIdValidator;
import dental_clinic.database.PatientDatabase;
import dental_clinic.dependency_injection.DIComponent;
import dental_clinic.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class ContainsDatabaseIdService {

    @DIDependency private final PatientDatabase patientDatabase;
    @DIDependency private ContainsDatabaseIdValidator containsDatabaseIdValidator;

    public ContainsDatabaseIdService(PatientDatabase patientDatabase, ContainsDatabaseIdValidator containsDatabaseIdValidator) {
        this.patientDatabase = patientDatabase;
        this.containsDatabaseIdValidator = containsDatabaseIdValidator;
    }

    public ContainsDatabaseIdResponse execute(ContainsDatabaseIdRequest containsDatabaseIdRequest){

        List<CoreError> errors = containsDatabaseIdValidator.validate(containsDatabaseIdRequest);

        if (!errors.isEmpty()){
            return new ContainsDatabaseIdResponse(errors);
        }

        if (patientDatabase.containsPatientWithSpecificId(containsDatabaseIdRequest.getId())){
            return new ContainsDatabaseIdResponse(containsDatabaseIdRequest.getId());
        }

        errors.add(new CoreError("id = ", "Database doesn't contain patient with id"));
        return new ContainsDatabaseIdResponse(errors);
    }

    private PersonalData getCurrentPatientPersonalData(int index){
        return patientDatabase.getPatients().get(index).getPersonalData();
    }
}