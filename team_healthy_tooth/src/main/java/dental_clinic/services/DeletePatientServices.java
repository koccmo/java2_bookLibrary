package dental_clinic.services;

import dental_clinic.database.PatientDatabase;
import dental_clinic.domain.PersonalData;

public class DeletePatientServices {

    private final PatientDatabase patientDatabase;

    public DeletePatientServices(PatientDatabase patientDatabase) {
        this.patientDatabase = patientDatabase;
    }

    public boolean deletePatient(long id){
        for (int i = 0; i < patientDatabase.getPatientList().size(); i++){
            if (getCurrentPatientPersonalData(i).getId() == id){
                patientDatabase.deletePatient(id);
                return true;
            }
        }
        return false;
    }

    private PersonalData getCurrentPatientPersonalData(int index){
        return patientDatabase.getPatientList().get(index).getPersonalData();
    }
}
