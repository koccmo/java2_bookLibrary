package dental_clinic.database;

import dental_clinic.domain.Patient;
import dental_clinic.domain.PersonalData;
import dental_clinic.domain.ToothStatus;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void testGetPatients(){
        List<Patient> expectedList = new ArrayList<>();
        Patient patient = new Patient(personalData1);
        expectedList.add(patient);
        assertTrue(patientDatabase.getPatients().equals(expectedList));
    }

    @Test
    public void testAddPatient(){
        patientDatabase.addPatient(personalData2);
        patientDatabase.getPatients().contains(personalData2);
        patientDatabase.getPatients().contains(personalData1);
        assertTrue(patientDatabase.getPatients().size() == 2);
    }

    @Test
    public void testIdGeneration(){
        assertTrue(patientDatabase.getPatients().get(0).getPersonalData().getId() == 1);
        patientDatabase.addPatient(personalData2);
        assertTrue(patientDatabase.getPatients().get(1).getPersonalData().getId() == 2);
        assertTrue(personalData2.getId() == 2);
    }

    @Test
    public void testDeletePatient(){
        assertTrue(patientDatabase.getPatients().size() == 1);
        patientDatabase.deletePatient(7);
        assertTrue(patientDatabase.getPatients().size() == 1);
        patientDatabase.deletePatient(1);
        assertFalse(patientDatabase.getPatients().contains(personalData1));
        assertTrue(patientDatabase.getPatients().size() == 0);
    }

    @Test
    public void testGetSpecificPatientHistory(){
        assertTrue(patientDatabase.getSpecificPatientHistory(1).isPresent());
        assertTrue(patientDatabase.getSpecificPatientHistory(8).isEmpty());

        Patient patient = new Patient(personalData1);
        assertTrue(patientDatabase.getSpecificPatientHistory(1).get().equals(patient));
    }

    @Test
    public void testFindPatientBySurname(){
        List <Patient> expectedList = new ArrayList<>();
        Patient patient = new Patient(personalData1);
        expectedList.add(patient);
        assertTrue(patientDatabase.findPatientBySurname("Bobbins").equals(expectedList));
        assertTrue(patientDatabase.findPatientBySurname("BOBBINS").equals(expectedList));
        assertTrue(patientDatabase.findPatientBySurname("Bob").equals(expectedList));
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

    @Test
    public void testAddVisitJowlData(){
        patientDatabase.addVisit(1, 11, Optional.of("bolit"), ToothStatus.FASETE, doctor1);

        List <ToothStatus> expectedList = new ArrayList<>();
        expectedList.add(ToothStatus.HEALTHY);
        expectedList.add(ToothStatus.FASETE);
        assertTrue(patientDatabase.getPatients().get(0).getJowl().getJowl().get(11).equals(expectedList));
    }

    @Test
    public void testAddVisitVisitData(){
        patientDatabase.addVisit(1, 11, Optional.of("bolit"), ToothStatus.FASETE, doctor1);

        assertTrue(patientDatabase.getPatients().get(0).getVisits().size() == 1);
        assertTrue(patientDatabase.getPatients().get(0).getVisits().get(0).getToothNumber() == 11);
        assertTrue(patientDatabase.getPatients().get(0).getVisits().get(0).getComment().equals(Optional.of("bolit")));
        assertTrue(patientDatabase.getPatients().get(0).getVisits().get(0).getToothStatus().equals(ToothStatus.FASETE));
        assertTrue(patientDatabase.getPatients().get(0).getVisits().get(0).getDoctor().equals(doctor1));

    }
}