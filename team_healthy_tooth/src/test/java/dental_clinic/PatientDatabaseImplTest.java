package dental_clinic;

import dental_clinic.domain.Patient;
import dental_clinic.domain.PersonalData;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class PatientDatabaseImplTest extends TestCase {

    PatientDatabaseImpl database;
    PersonalData personalData = new PersonalData(
            "name", "surname", "phone", "pCode"
    );

    public void setUp() throws Exception {
        super.setUp();
        database = new PatientDatabaseImpl();
        database.addPatient(personalData, "doctor1");
    }

    public void testAddPatient() {
        //this personal data is already in database from setUp
        assertFalse(database.addPatient(personalData, "doctor"));
    }

    public void testDeletePatient() {
        assertTrue(database.deletePatient(1L));
        assertFalse(database.deletePatient(1L));
    }

    public void testPrintDatabase() {
        assertTrue(database.printDatabase());
        database.deletePatient(1L);
        assertFalse(database.printDatabase());
    }

    public void testPrintSpecificPatientHistory() {
        //id555 is not in database
        assertTrue(database.printSpecificPatientHistory(1L));
        assertFalse(database.printSpecificPatientHistory(555L));
    }

    /*

    для теста нужно получить Patient, тк методы find возвращают список Patient'ов
    было бы круто придумать геттер пациентов для тестов в databaseImpl

    public void testFindPatientBySurname() {
        List<Patient> result = database.findPatientBySurname("surname");
        assertTrue(result.contains(personalData));
    }

    public void testFindPatientByPersonalCode() {
        List<Patient> result = database.findPatientByPersonalCode("pCode");
        assertTrue(result.contains(personalData));
    }


    public void testUpdateJowlData() {
    }
     */
}