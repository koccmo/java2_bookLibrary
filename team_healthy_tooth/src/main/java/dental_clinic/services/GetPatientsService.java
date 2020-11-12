package dental_clinic.services;

import dental_clinic.database.PatientDatabase;
import dental_clinic.domain.Patient;

import java.util.List;

public class GetPatientsService {

    private PatientDatabase patientDatabase;

    public GetPatientsService(PatientDatabase patientDatabase) {
        this.patientDatabase = patientDatabase;
    }

    public List<Patient>execute(){
        return  patientDatabase.getPatients();
    }
}
