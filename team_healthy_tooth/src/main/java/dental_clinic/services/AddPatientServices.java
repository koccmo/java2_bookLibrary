package dental_clinic.services;

import dental_clinic.database.PatientDatabase;
import dental_clinic.domain.PersonalData;

import java.util.Optional;

public class AddPatientServices {

    private PatientDatabase patientDatabase;

    public AddPatientServices(PatientDatabase patientDatabase) {
        this.patientDatabase = patientDatabase;
    }

    public boolean addPatient (PersonalData personalData){
        if (containsPatientDatabasePersonalData(personalData)){
            return false;
        }else{
            patientDatabase.addPatient(personalData);
            return true;
        }
    }

    private boolean containsPatientDatabasePersonalData (PersonalData personalData){
        Optional<PersonalData> result = patientDatabase.getPatientList().stream()
                .map(patient -> patient.getPersonalData())
                .filter(patient1 -> patient1.equals(personalData))
                .findAny();
        return result.isPresent();
    }
}