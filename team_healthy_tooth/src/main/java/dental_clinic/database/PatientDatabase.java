package dental_clinic.database;

import dental_clinic.domain.Patient;
import dental_clinic.domain.PersonalData;
import dental_clinic.domain.ToothStatus;

import java.util.List;
import java.util.Optional;

public interface PatientDatabase {

    List<Patient>getPatientList();

    void addPatient(PersonalData personalData);

    void deletePatient(long id);

    boolean printDatabase();

    boolean printSpecificPatientHistory(long id);

    List<Patient> findPatientBySurname(String surname);

    List<Patient> findPatientByPersonalCode(String personalCode);

    boolean addVisit(long id, int toothNumber, Optional<String> comment, ToothStatus toothStatus);



}
