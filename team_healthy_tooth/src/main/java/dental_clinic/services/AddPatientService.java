package dental_clinic.services;

import dental_clinic.database.PatientDatabase;
import dental_clinic.domain.PersonalData;

import java.util.Optional;

public class AddPatientService {

    private PatientDatabase patientDatabase;

    public AddPatientService(PatientDatabase patientDatabase) {
        this.patientDatabase = patientDatabase;
    }

    public boolean addPatient (PersonalData personalData){
        if (containsDatabasePatientPersonalData(personalData)){
            return false;
        }else{
            patientDatabase.addPatient(personalData);
            return true;
        }
    }

    private boolean containsDatabasePatientPersonalData(PersonalData personalData){
        Optional<PersonalData> result = patientDatabase.getPatients().stream()
                .map(patient -> patient.getPersonalData())
                .filter(patient1 -> patient1.equals(personalData))
                .findAny();
        return result.isPresent();
    }
}