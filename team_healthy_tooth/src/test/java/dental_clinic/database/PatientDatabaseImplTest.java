package dental_clinic.database;

import dental_clinic.domain.Patient;
import dental_clinic.domain.PersonalData;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PatientDatabaseImplTest {
    PersonalData personalData1 = new PersonalData("Bob", "Bobbins", "12345678", "12345678900");
    PersonalData personalData2 = new PersonalData("John", "Joninns", "12345670", "12345678999");
    String doctor1 = "Doktor Zlo";
    String doctor2 = "Doktor Haus";
    PatientDatabase patientDatabase = new PatientDatabaseImpl();
    @Before
    public void init(){
        patientDatabase.addPatient(personalData1);
    }

    @Test
    public void testGetList(){
        List<Patient> expectedList = new ArrayList<>();
        Patient patient = new Patient(personalData1);
        expectedList.add(patient);
        assertTrue(patientDatabase.getPatientList().equals(expectedList));
    }

    @Test
    public void testAddPatient(){
        patientDatabase.addPatient(personalData2);
        patientDatabase.getPatientList().contains(personalData2);
    }

    @Test
    public void testIdGeneration(){
        assertTrue(patientDatabase.getPatientList().get(0).getPersonalData().getId() == 1);
        patientDatabase.addPatient(personalData2);
        assertTrue(patientDatabase.getPatientList().get(1).getPersonalData().getId() == 2);
    }

    @Test
    public void testDeletePatient(){
        assertTrue(patientDatabase.getPatientList().size() == 1);
        patientDatabase.deletePatient(1);
        assertTrue(patientDatabase.getPatientList().size() == 0);
    }

    @Test
    public void testFindPatientBySurname(){
        List <Patient> expectedList = new ArrayList<>();
        Patient patient = new Patient(personalData1);
        expectedList.add(patient);
        assertTrue(patientDatabase.findPatientBySurname("Bobbins").equals(expectedList));
        assertTrue(patientDatabase.findPatientBySurname("Jhonson").equals(new ArrayList<>()));
    }

    @Test
    public void testFindPatientByPersonalCode(){
        List <Patient> expectedList = new ArrayList<>();
        Patient patient = new Patient(personalData1);
        expectedList.add(patient);
        assertTrue(patientDatabase.findPatientByPersonalCode("12345678900").equals(expectedList));
        assertTrue(patientDatabase.findPatientByPersonalCode("12345678907").equals(new ArrayList<>()));
    }

}