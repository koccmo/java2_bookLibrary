package dental_clinic.core.services;

import dental_clinic.core.domain.Jowl;
import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.requests.GetPatientCardRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.GetPatientCardResponse;
import dental_clinic.database.PatientDatabase;

import java.util.*;

public class GetPatientCardService {
    
    private final PatientDatabase patientDatabase;
    private final GetPatientCardRequestValidator getPatientCardRequestValidator;

    public GetPatientCardService(PatientDatabase patientDatabase, GetPatientCardRequestValidator getPatientCardRequestValidator) {
        this.patientDatabase = patientDatabase;
        this.getPatientCardRequestValidator = getPatientCardRequestValidator;
    }
    
    public GetPatientCardResponse execute (GetPatientCardRequest getPatientCardRequest){
        List<CoreError> errors = getPatientCardRequestValidator.validate(getPatientCardRequest);
        
        if (!errors.isEmpty()){
            return new GetPatientCardResponse(errors);
        }
        
        if (!patientDatabase.containsPatientWithSpecificId(getPatientCardRequest.getId())){
            errors.add(new CoreError("database", "Database doesn't contain patient with id " +
                    getPatientCardRequest.getId()));
            return new GetPatientCardResponse(errors);
        }
        
        Patient patientCard = createPatientCard(getPatientCardRequest);
        return new GetPatientCardResponse(patientCard);
    }

    private Patient createPatientCard(GetPatientCardRequest getPatientCardRequest){
        Patient newPatient = patientDatabase.getPatientCard(getPatientCardRequest.getId()).get();
        Jowl currentStatusOfJowl = new Jowl();
        Jowl specificPatientJowl = newPatient.getJowl();
        for (Integer key : specificPatientJowl.getJowl().keySet()){
            currentStatusOfJowl.getJowl().put(key,
                    new ArrayList<>(Arrays.asList(specificPatientJowl.getJowl().get(key).get(specificPatientJowl.getJowl().get(key).size() - 1))));
        }
        newPatient.setJowl(currentStatusOfJowl);
        return newPatient;
    }
    
}
