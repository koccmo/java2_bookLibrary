package dental_clinic.core.services;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.GetPatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.GetPatientsResponse;
import dental_clinic.database.PatientDatabase;

import java.util.ArrayList;
import java.util.List;


public class GetPatientsService {

    private PatientDatabase patientDatabase;

    public GetPatientsService(PatientDatabase patientDatabase) {
        this.patientDatabase = patientDatabase;
    }

    public GetPatientsResponse execute(GetPatientRequest getPatientRequest){
        List <CoreError> errors = new ArrayList<>();

        if (patientDatabase.getPatients().isEmpty()){
            errors.add(new CoreError("database", "Database is empty"));
            return new GetPatientsResponse(errors, new ArrayList<>());
        }

        List<Patient>patients = patientDatabase.getPatients();
        return new GetPatientsResponse(patients);
    }
}
