package dental_clinic.services;

import dental_clinic.database.PatientDatabase;
import dental_clinic.domain.ToothStatus;

import java.util.Optional;

public class AddVisitService {

    private final PatientDatabase patientDatabase;

    public AddVisitService(PatientDatabase patientDatabase) {
        this.patientDatabase = patientDatabase;
    }

    public boolean execute(long id, int toothNumber, Optional<String> comment, ToothStatus toothStatus){
        for (int i = 0; i < patientDatabase.getPatients().size(); i++){
            if (patientDatabase.getPatients().get(i).getPersonalData().getId() == id){
                patientDatabase.addVisit(id, toothNumber, comment, toothStatus);
                return true;
            }
        }
        return false;
    }
}
