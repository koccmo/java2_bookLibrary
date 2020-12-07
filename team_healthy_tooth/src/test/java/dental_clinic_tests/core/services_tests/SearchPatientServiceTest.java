package dental_clinic_tests.core.services_tests;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;
import dental_clinic.core.requests.SearchPatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.SearchPatientResponse;
import dental_clinic.core.services.SearchPatientService;
import dental_clinic.core.services.validators.SearchPatientRequestValidator;
import dental_clinic.database.PatientDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchPatientServiceTest {

    @Mock
    private PatientDatabase patientDatabase;
    @Mock
    private SearchPatientRequestValidator searchPatientRequestValidator;
    @InjectMocks
    private SearchPatientService searchPatientService;

    private Ordering validOrdering = new Ordering("surname", "ASC");
    private Paging validPaging = new Paging(1, 2);

    @Test
    public void testSearchRequestEmpty(){
        CoreError expectedError = new CoreError("search", "Not valid input for search");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("", "", validOrdering, validPaging);

        Mockito.when(searchPatientRequestValidator.validate(searchPatientRequest)).thenReturn(errors);

        SearchPatientResponse searchPatientResponse = searchPatientService.execute(searchPatientRequest);

        assertTrue(searchPatientResponse.hasErrors());
        assertTrue(searchPatientResponse.getErrors().contains(expectedError));
        assertTrue(searchPatientResponse.getErrors().size() == 1);
    }

    @Test
    public void testNoPatientWithSpecificNameAndSurname(){
        CoreError expectedError =
                new CoreError("database", "Database doesn't contain patient with name Bob and surname Bobbins");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Bob", "Bobbins", validOrdering, validPaging);

        Mockito.when(searchPatientRequestValidator.validate(searchPatientRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientDatabase.findPatientsByNameAndSurname(searchPatientRequest.getName(),
                searchPatientRequest.getSurname())).thenReturn(new ArrayList<>());

        SearchPatientResponse searchPatientResponse = searchPatientService.execute(searchPatientRequest);

        assertTrue(searchPatientResponse.hasErrors());
        assertTrue(searchPatientResponse.getErrors().contains(expectedError));
        assertTrue(searchPatientResponse.getErrors().size() == 1);
    }

    @Test
    public void testNoPatientWithSpecificName(){
        CoreError expectedError =
                new CoreError("database", "Database doesn't contain patient with name Bob");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Bob", null, validOrdering, validPaging);

        Mockito.when(searchPatientRequestValidator.validate(searchPatientRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientDatabase.findPatientByName(searchPatientRequest.getName())).thenReturn(new ArrayList<>());

        SearchPatientResponse searchPatientResponse = searchPatientService.execute(searchPatientRequest);

        assertTrue(searchPatientResponse.hasErrors());
        assertTrue(searchPatientResponse.getErrors().contains(expectedError));
        assertTrue(searchPatientResponse.getErrors().size() == 1);
    }

    @Test
    public void testNoPatientWithSpecificSurname(){
        CoreError expectedError =
                new CoreError("database", "Database doesn't contain patient with surname Bobbins");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("", "Bobbins", validOrdering, validPaging);

        Mockito.when(searchPatientRequestValidator.validate(searchPatientRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientDatabase.findPatientsBySurname(searchPatientRequest.getSurname())).thenReturn(new ArrayList<>());

        SearchPatientResponse searchPatientResponse = searchPatientService.execute(searchPatientRequest);

        assertTrue(searchPatientResponse.hasErrors());
        assertTrue(searchPatientResponse.getErrors().contains(expectedError));
        assertTrue(searchPatientResponse.getErrors().size() == 1);
    }

    @Test
    public void testSearchByName(){
        PersonalData personalData1 = new PersonalData("Bob", "Z", "12345678", "25052512345");
        PersonalData personalData2 = new PersonalData("Bob", "A", "12345678", "25052512345");
        Patient patient1 = new Patient(personalData1);
        Patient patient2 = new Patient(personalData2);
        List<Patient>patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Bob", null, validOrdering, validPaging);

        Mockito.when(searchPatientRequestValidator.validate(searchPatientRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientDatabase.findPatientByName(searchPatientRequest.getName())).thenReturn(patients);

        SearchPatientResponse searchPatientResponse = searchPatientService.execute(searchPatientRequest);

        assertFalse(searchPatientResponse.hasErrors());
        assertTrue(searchPatientResponse.getPatients().size() == 2);
        assertTrue(searchPatientResponse.getPatients().contains(patient1));
        assertTrue(searchPatientResponse.getPatients().contains(patient2));
        assertTrue(searchPatientResponse.getPatients().get(0).equals(patient2));
    }

    @Test
    public void testSearchByNamePageSize1(){
        PersonalData personalData1 = new PersonalData("Bob", "Z", "12345678", "25052512345");
        PersonalData personalData2 = new PersonalData("Bob", "A", "12345678", "25052512345");
        Patient patient1 = new Patient(personalData1);
        Patient patient2 = new Patient(personalData2);
        List<Patient>patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Bob", null, new Ordering("surname", "DESC"), new Paging(1, 1));

        Mockito.when(searchPatientRequestValidator.validate(searchPatientRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientDatabase.findPatientByName(searchPatientRequest.getName())).thenReturn(patients);

        SearchPatientResponse searchPatientResponse = searchPatientService.execute(searchPatientRequest);

        assertFalse(searchPatientResponse.hasErrors());
        assertTrue(searchPatientResponse.getPatients().size() == 1);
        assertTrue(searchPatientResponse.getPatients().get(0).equals(patient1));
    }
}