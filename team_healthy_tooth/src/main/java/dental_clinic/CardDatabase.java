package dental_clinic;

import dental_clinic.domain.Patient;

public interface CardDatabase {

    boolean addPatient(Patient patient);

    boolean deletePatient(long id);

    void printDatabase();

    boolean printPatientCard(long id);

}
