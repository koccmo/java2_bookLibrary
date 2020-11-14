package dental_clinic.core.services;

import dental_clinic.database.PatientDatabase;
import dental_clinic.core.domain.Patient;

import java.util.List;
import java.util.stream.Collectors;

public class FindPatientsByPersonalCodeService {

    private final PatientDatabase patientDatabase;

    public FindPatientsByPersonalCodeService(PatientDatabase patientDatabase) {
        this.patientDatabase = patientDatabase;
    }

    public List<Patient> execute(String personalCode){
        return patientDatabase.getPatients().stream()
                .filter(patient -> patient.getPersonalData().getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }
}
