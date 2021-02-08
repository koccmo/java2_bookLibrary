package dental_clinic.core.services.patient;

import dental_clinic.core.requests.patient.GetPatientCardRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.GetPatientCardResponse;
import dental_clinic.core.validators.patient.GetPatientCardRequestValidator;
import dental_clinic.core.database.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GetPatientCardService {

    @Autowired private PatientRepository patientRepository;
    @Autowired
    private GetPatientCardRequestValidator getPatientCardRequestValidator;
    
    public GetPatientCardResponse execute (GetPatientCardRequest getPatientCardRequest){
        List<CoreError> errors = getPatientCardRequestValidator.validate(getPatientCardRequest);
        
        if (!errors.isEmpty()){
            return new GetPatientCardResponse(errors);
        }
        
        if (!patientRepository.containsPatientWithSpecificId(getPatientCardRequest.getId())){
            errors.add(new CoreError("database", "Database doesn't contain patient with id " +
                    getPatientCardRequest.getId()));
            return new GetPatientCardResponse(errors);
        }

        return new GetPatientCardResponse(patientRepository.getPatientCard(getPatientCardRequest.getId()).get());
    }

}
