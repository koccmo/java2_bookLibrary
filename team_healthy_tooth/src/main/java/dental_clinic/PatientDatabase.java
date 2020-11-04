package dental_clinic;

import dental_clinic.domain.PersonalData;
import dental_clinic.domain.ToothStatus;

import java.util.List;
import java.util.Optional;

public interface PatientDatabase {

    boolean addPatient(PersonalData personalData, String doctor);

    boolean deletePatient(long id);

    boolean printDatabase();

    boolean printSpecificPatientHistory(long id);

    List<PersonalData> findPatientBySurname (String surname);

    List <PersonalData> findPatientByPersonalCode (String personalCode);

    boolean updateJowlData (long id, int toothNumber, Optional<String> comment, ToothStatus toothStatus);

    boolean printPatientCardForVisit(long id);

}
