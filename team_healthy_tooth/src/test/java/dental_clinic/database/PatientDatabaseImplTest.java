package dental_clinic.database;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.domain.Visit;
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
        patientDatabase.deletePatient(7L);
        assertTrue(patientDatabase.getPatients().size() == 1);
        patientDatabase.deletePatient(1L);
        assertFalse(patientDatabase.getPatients().contains(personalData1));
        assertTrue(patientDatabase.getPatients().size() == 0);
    }

    @Test
    public void testGetSpecificPatientHistory(){
        assertTrue(patientDatabase.getSpecificPatientHistory(1L).isPresent());
        assertTrue(patientDatabase.getSpecificPatientHistory(8L).isEmpty());

        Patient patient = new Patient(personalData1);
        assertTrue(patientDatabase.getSpecificPatientHistory(1L).get().equals(patient));
    }

    @Test
    public void testFindPatientBySurname(){
        List <Patient> expectedList = new ArrayList<>();
        Patient patient = new Patient(personalData1);
        expectedList.add(patient);
        assertTrue(patientDatabase.findPatientsBySurname("Bobbins").equals(expectedList));
        assertTrue(patientDatabase.findPatientsBySurname("BOBBINS").equals(expectedList));
        assertTrue(patientDatabase.findPatientsBySurname("Bob").equals(expectedList));
        assertTrue(patientDatabase.findPatientsBySurname("Jhonson").equals(new ArrayList<>()));
    }

    @Test
    public void testFindPatientByPersonalCode(){
        List <Patient> expectedList = new ArrayList<>();
        Patient patient = new Patient(personalData1);
        expectedList.add(patient);
        assertTrue(patientDatabase.findPatientsByPersonalCode("12345678900").equals(expectedList));
        assertTrue(patientDatabase.findPatientsByPersonalCode("12345678907").equals(new ArrayList<>()));
    }

    @Test
    public void testAddVisitJowlData(){
        Visit newVisit = new Visit(11, Optional.of("bolit"), ToothStatus.FASETE, doctor1);
        patientDatabase.addVisit(1L, newVisit);

        List <ToothStatus> expectedList = new ArrayList<>();
        expectedList.add(ToothStatus.HEALTHY);
        expectedList.add(ToothStatus.FASETE);
        assertTrue(patientDatabase.getPatients().get(0).getJowl().getJowl().get(11).equals(expectedList));
    }

    @Test
    public void testAddVisitVisitData(){
        Visit newVisit = new Visit(11, Optional.of("bolit"), ToothStatus.FASETE, doctor1);
        patientDatabase.addVisit(1L, newVisit);

        assertTrue(patientDatabase.getPatients().get(0).getVisits().size() == 1);
        assertTrue(patientDatabase.getPatients().get(0).getVisits().get(0).getToothNumber() == 11);
        assertTrue(patientDatabase.getPatients().get(0).getVisits().get(0).getComment().equals(Optional.of("bolit")));
        assertTrue(patientDatabase.getPatients().get(0).getVisits().get(0).getToothStatus().equals(ToothStatus.FASETE));
        assertTrue(patientDatabase.getPatients().get(0).getVisits().get(0).getDoctor().equals(doctor1));

    }
}