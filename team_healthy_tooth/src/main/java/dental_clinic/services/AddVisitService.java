package dental_clinic.services;

import dental_clinic.database.PatientDatabase;
import dental_clinic.domain.ToothStatus;
import dental_clinic.domain.Visit;

import java.util.Optional;

public class AddVisitService {

    private final PatientDatabase patientDatabase;

    public AddVisitService(PatientDatabase patientDatabase) {
        this.patientDatabase = patientDatabase;
    }

    public boolean execute(long id, int toothNumber, Optional<String> comment, ToothStatus toothStatus, String doctor){
        for (int i = 0; i < patientDatabase.getPatients().size(); i++){
            if (isSpecificPatient(i, id)){
                Visit visit = new Visit(toothNumber, comment, toothStatus, doctor);
                patientDatabase.getPatients().get(i).addVisit(visit);
                patientDatabase.getPatients().get(i).updateJowl(toothNumber, toothStatus);
                return true;
            }
        }
        return false;
    }

    private boolean isSpecificPatient (int index, long id) {
        return patientDatabase.getPatients().get(index).getPersonalData().getId() == id;
    }
}
