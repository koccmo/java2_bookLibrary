package dental_clinic_tests.database_tests;
/*
import dental_clinic.core.domain.*;
import dental_clinic.core.database.patient.PatientRepository;
import dental_clinic.core.database.patient.InMemoryPatientRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class InMemoryPatientRepositoryImplTest {
    PersonalData personalData1 = new PersonalData("Bob", "Bobbins", "12345678", "12345678900");
    PersonalData personalData2 = new PersonalData("John", "Joninns", "12345670", "12345678999");
    Doctor doctor1 = new Doctor("Doctor", "Zlo", "12345678");
    Doctor doctor2 = new Doctor("Doctor", "Haus", "12345678");
    PatientRepository patientRepository = new InMemoryPatientRepositoryImpl();
    private List<Manipulation>manipulations = new ArrayList<>();
    private List<Long> manipulationIds = new ArrayList<>();
    @Before
    public void init(){
        patientRepository.addPatient(personalData1);
    }

    @Test
    public void testGetPatients(){
        List<Patient> expectedList = new ArrayList<>();
        Patient patient = new Patient(personalData1);
        expectedList.add(patient);
        assertTrue(patientRepository.getPatients().equals(expectedList));
    }

    @Test
    public void testAddPatient(){
        patientRepository.addPatient(personalData2);
        patientRepository.getPatients().contains(personalData2);
        patientRepository.getPatients().contains(personalData1);
        assertTrue(patientRepository.getPatients().size() == 2);
    }

    @Test
    public void testIdGeneration(){
        assertTrue(patientRepository.getPatients().get(0).getPersonalData().getId() == 1);
        patientRepository.addPatient(personalData2);
        assertTrue(patientRepository.getPatients().get(1).getPersonalData().getId() == 2);
        assertTrue(personalData2.getId() == 2);
    }

    @Test
    public void testDeletePatient(){
        assertTrue(patientRepository.getPatients().size() == 1);
        patientRepository.deletePatient(7L);
        assertTrue(patientRepository.getPatients().size() == 1);
        patientRepository.deletePatient(1L);
        assertFalse(patientRepository.getPatients().contains(personalData1));
        assertTrue(patientRepository.getPatients().size() == 0);
    }

    @Test
    public void testGetSpecificPatientHistory(){
        assertTrue(patientRepository.getSpecificPatientHistory(1L).isPresent());
        assertTrue(patientRepository.getSpecificPatientHistory(8L).isEmpty());

        Patient patient = new Patient(personalData1);
        assertTrue(patientRepository.getSpecificPatientHistory(1L).get().equals(patient));
    }

    @Test
    public void testFindPatientBySurname(){
        List <Patient> expectedList = new ArrayList<>();
        Patient patient = new Patient(personalData1);
        expectedList.add(patient);
        assertTrue(patientRepository.findPatientsBySurname("Bobbins").equals(expectedList));
        assertTrue(patientRepository.findPatientsBySurname("BOBBINS").equals(expectedList));
        assertTrue(patientRepository.findPatientsBySurname("Bob").equals(expectedList));
        assertTrue(patientRepository.findPatientsBySurname("Jhonson").equals(new ArrayList<>()));
    }

    @Test
    public void testFindPatientByPersonalCode(){
        Patient expectedPatient = new Patient(personalData1);
        assertTrue(patientRepository.findPatientsByPersonalCode("12345678900").get(0).equals(expectedPatient));
        assertTrue(patientRepository.findPatientsByPersonalCode("12345678907").isEmpty());
    }

    @Test
    public void testAddVisitJowlData(){
        Visit newVisit = new Visit(1L, 11, Optional.of("bolit"), ToothStatus.FASETE, doctor1, manipulations, new Date());
        patientRepository.addVisit(1L, newVisit);

        List <ToothStatus> expectedList = new ArrayList<>();
        expectedList.add(ToothStatus.HEALTHY);
        expectedList.add(ToothStatus.FASETE);
        assertTrue(patientRepository.getPatients().get(0).getJowl().getJowl().get(11).equals(expectedList));
    }

    @Test
    public void testAddVisitVisitData(){
        Visit newVisit = new Visit(1L, 11, "bolit", ToothStatus.FASETE, doctor1, manipulations, new Date());
        patientRepository.addVisit(1L, newVisit);

        assertTrue(patientRepository.getPatients().get(0).getVisits().size() == 1);
        assertTrue(patientRepository.getPatients().get(0).getVisits().get(0).getToothNumber() == 11);
        assertTrue(patientRepository.getPatients().get(0).getVisits().get(0).getComment().equals("bolit"));
        assertTrue(patientRepository.getPatients().get(0).getVisits().get(0).getToothStatus().equals(ToothStatus.FASETE));
        assertTrue(patientRepository.getPatients().get(0).getVisits().get(0).getDoctor().equals(doctor1));

    }
}*/