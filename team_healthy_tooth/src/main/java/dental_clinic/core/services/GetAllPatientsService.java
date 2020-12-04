package dental_clinic.core.services;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.GetAllPatientsRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.GetAllPatientsResponse;
import dental_clinic.core.services.validators.GetAllPatientsRequestValidator;
import dental_clinic.database.PatientDatabase;
import dental_clinic.dependency_injection.DIComponent;
import dental_clinic.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class GetAllPatientsService {

    @DIDependency private PatientDatabase patientDatabase;
    @DIDependency private GetAllPatientsRequestValidator getAllPatientsRequestValidator;

    public GetAllPatientsService(PatientDatabase patientDatabase, GetAllPatientsRequestValidator getAllPatientsRequestValidator) {
        this.patientDatabase = patientDatabase;
        this.getAllPatientsRequestValidator = getAllPatientsRequestValidator;
    }

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
