package dental_clinic_tests.core.services_tests;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.patient.SearchPatientByPersonalCodeRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.SearchPatientByPersonalCodeResponse;
import dental_clinic.core.services.patient.SearchPatientsByPersonalCodeService;
import dental_clinic.core.validators.patient.SearchPatientByPersonalCodeRequestValidator;
import dental_clinic.database.in_memory.patient.PatientDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchPatientByPersonalCodeServiceTest {

    @Mock
    private SearchPatientByPersonalCodeRequestValidator searchPatientByPersonalCodeRequestValidator;
    @Mock
    private PatientDatabase patientDatabase;
    @InjectMocks
    private SearchPatientsByPersonalCodeService searchPatientsByPersonalCodeService;

    @Test
    public void testNotValidPersonalCode(){
        SearchPatientByPersonalCodeRequest searchPatientByPersonalCodeRequest = new SearchPatientByPersonalCodeRequest("jokjl");
        CoreError expectedError = new CoreError("Personal data : personal code",
                "Invalid input for personal code!");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        Mockito.when(searchPatientByPersonalCodeRequestValidator.validate(searchPatientByPersonalCodeRequest)).thenReturn(errors);

        SearchPatientByPersonalCodeResponse searchPatientByPersonalCodeResponse = searchPatientsByPersonalCodeService.execute(searchPatientByPersonalCodeRequest);

        assertTrue(searchPatientByPersonalCodeResponse.hasErrors());
        assertTrue(searchPatientByPersonalCodeResponse.getErrors().size() == 1);
        assertTrue(searchPatientByPersonalCodeResponse.getErrors().contains(expectedError));
    }

    @Test
    public void testNoIdInDatabase(){
        SearchPatientByPersonalCodeRequest searchPatientByPersonalCodeRequest = new SearchPatientByPersonalCodeRequest("25052512345");
        CoreError expectedError = new CoreError("database", "Database doesn't contain patient with personal code 25052512345");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        Mockito.when(searchPatientByPersonalCodeRequestValidator.validate(searchPatientByPersonalCodeRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientDatabase.findPatientsByPersonalCode("25052512345")).thenReturn(Optional.empty());
        SearchPatientByPersonalCodeResponse searchPatientByPersonalCodeResponse = searchPatientsByPersonalCodeService.execute(searchPatientByPersonalCodeRequest);

        assertTrue(searchPatientByPersonalCodeResponse.hasErrors());
        assertTrue(searchPatientByPersonalCodeResponse.getErrors().size() == 1);
        assertTrue(searchPatientByPersonalCodeResponse.getErrors().contains(expectedError));
    }

    @Test
    public void testSuccessfullyFound(){
        SearchPatientByPersonalCodeRequest searchPatientByPersonalCodeRequest = new SearchPatientByPersonalCodeRequest("25052512345");

        Mockito.when(searchPatientByPersonalCodeRequestValidator.validate(searchPatientByPersonalCodeRequest)).thenReturn(new ArrayList<>());
        Patient patient = new Patient(new PersonalData("Name", "Surname", "12345678", "25052512345"));
        Optional<Patient> patientResult = Optional.of(patient);
        Mockito.when(patientDatabase.findPatientsByPersonalCode("25052512345")).thenReturn(patientResult);
        SearchPatientByPersonalCodeResponse searchPatientByPersonalCodeResponse = searchPatientsByPersonalCodeService.execute(searchPatientByPersonalCodeRequest);

        assertFalse(searchPatientByPersonalCodeResponse.hasErrors());

        assertTrue(searchPatientByPersonalCodeResponse.getFoundPatient().getPersonalData().getPersonalCode().equals("25052512345"));
    }
}