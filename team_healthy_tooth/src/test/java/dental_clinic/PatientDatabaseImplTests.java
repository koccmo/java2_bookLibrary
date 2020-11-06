package dental_clinic;

import dental_clinic.database.PatientDatabase;
import dental_clinic.database.PatientDatabaseImpl;
import dental_clinic.domain.Patient;
import dental_clinic.domain.PersonalData;
import dental_clinic.services.AddPatientServices;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PatientDatabaseImplTests {

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
    public void testAddPatientMethod(){
        AddPatientServices addPatientServices = new AddPatientServices(patientDatabase);
        assertTrue(addPatientServices.addPatient(personalData2));
        assertTrue(patientDatabase.getPatientList().size() == 2);

        assertFalse(addPatientServices.addPatient(personalData2));
    }

    @Test
    public void testIdGeneration(){
        PersonalData personalDataOfFirstAddedPatient = patientDatabase.getPatientList().get(0).getPersonalData();
        assertTrue(personalDataOfFirstAddedPatient.getId() == 1);

        patientDatabase.addPatient(personalData2);
        PersonalData personalDataOfSecondAddedPatient = patientDatabase.getPatientList().get(1).getPersonalData();
        assertTrue(personalDataOfSecondAddedPatient.getId() == 2);
    }

    @Test
    public void testGetPatientList(){
        List<Patient> currentList= patientDatabase.getPatientList();
        List<Patient> expectedList = new ArrayList<>();
        Patient patient = new Patient(personalData1);
        expectedList.add(patient);

        assertTrue(currentList.size() == 1);
        assertTrue(currentList.equals(expectedList));
    }

    @Test
    public void testFindPatientBySurname(){
        List <Patient> expectedList = new ArrayList<>();
        Patient patient = new Patient(personalData1);
        expectedList.add(patient);
        assertTrue(patientDatabase.findPatientBySurname("Bobbins").equals(expectedList));
        assertTrue(patientDatabase.findPatientBySurname("Robertson").equals(new ArrayList<>()));
    }

     @Test
    public void testFindPatientByPersonalCode(){
        List <Patient> expectedList = new ArrayList<>();
        Patient patient = new Patient(personalData1);
        expectedList.add(patient);
        assertTrue(patientDatabase.findPatientByPersonalCode("12345678900").equals(expectedList));
        assertTrue(patientDatabase.findPatientByPersonalCode("12345678945").equals(new ArrayList<>()));
     }

    @Test
    public void testAddVisit(){

    }

     //@Override
    //    public boolean addVisit(long id, int toothNumber, Optional<String> comment, ToothStatus toothStatus) {
    //        for (int i = 0; i < patientList.size(); i++){
    //            if (patientList.get(i).getPersonalData().getId() == id){
    //                patientList.get(i).getJowl().updateJowl(toothNumber, comment, toothStatus);
    //                return true;
    //            }
    //        }
    //        return false;
    //    }



}