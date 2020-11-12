package dental_clinic.services;

import dental_clinic.database.PatientDatabase;
import dental_clinic.domain.Patient;

import java.util.List;
import java.util.stream.Collectors;

public class FindPatientBySurnameService {

    private final PatientDatabase patientDatabase;

    public FindPatientBySurnameService(PatientDatabase patientDatabase) {
        this.patientDatabase = patientDatabase;
    }

    public List<Patient> execute(String surname){
        return patientDatabase.getPatients().stream()
                .filter(patient -> patient.getPersonalData().getSurname().toLowerCase().startsWith(surname.toLowerCase()))
                .collect(Collectors.toList());
    }
}
