package dental_clinic.services;

import dental_clinic.database.PatientDatabase;
import dental_clinic.domain.PersonalData;

public class DeletePatientService {

    private final PatientDatabase patientDatabase;

    public DeletePatientService(PatientDatabase patientDatabase) {
        this.patientDatabase = patientDatabase;
    }

    public boolean deletePatient(long id){
        for (int i = 0; i < patientDatabase.getPatients().size(); i++){
            if (getCurrentPatientPersonalData(i).getId() == id){
                patientDatabase.deletePatient(id);
                return true;
            }
        }
        return false;
    }

    private PersonalData getCurrentPatientPersonalData(int index){
        return patientDatabase.getPatients().get(index).getPersonalData();
    }
}
