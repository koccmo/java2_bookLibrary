package dental_clinic.services;

import dental_clinic.database.PatientDatabase;
import dental_clinic.domain.Patient;

import java.util.List;
import java.util.stream.Collectors;

public class FindPatientByPersonalCodeService {

    private final PatientDatabase patientDatabase;

    public FindPatientByPersonalCodeService(PatientDatabase patientDatabase) {
        this.patientDatabase = patientDatabase;
    }

    public List<Patient> execute(String personalCode){
        return patientDatabase.getPatients().stream()
                .filter(patient -> patient.getPersonalData().getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }
}
