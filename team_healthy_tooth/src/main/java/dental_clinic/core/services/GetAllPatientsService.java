package dental_clinic.core.services;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.GetAllPatientsRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.GetAllPatientsResponse;
import dental_clinic.core.services.validators.GetAllPatientsRequestValidator;
import dental_clinic.database.PatientDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllPatientsService {

    @Autowired
    private PatientDatabase patientDatabase;
    @Autowired private GetAllPatientsRequestValidator getAllPatientsRequestValidator;

    public GetAllPatientsResponse execute(GetAllPatientsRequest getAllPatientsRequest){
        List <CoreError> errors = getAllPatientsRequestValidator.validate(getAllPatientsRequest);

        if (!errors.isEmpty()){
            return new GetAllPatientsResponse(errors, new ArrayList<>());
        }

        if (patientDatabase.getPatients().isEmpty()){
            errors.add(new CoreError("database", "Database is empty"));
            return new GetAllPatientsResponse(errors, new ArrayList<>());
        }

        List<Patient>patients = patientDatabase.getPatients();
        return new GetAllPatientsResponse(patients);
    }
}
