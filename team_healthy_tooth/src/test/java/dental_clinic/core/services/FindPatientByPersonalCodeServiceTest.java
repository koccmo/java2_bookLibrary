package dental_clinic.core.services;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.FindPatientByPersonalCodeRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.FindPatientByPersonalCodeResponse;
import dental_clinic.database.PatientDatabase;
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
public class FindPatientByPersonalCodeServiceTest {

    @Mock
    private FindPatientByPersonalCodeValidator findPatientByPersonalCodeValidator;
    @Mock
    private PatientDatabase patientDatabase;
    @InjectMocks
    private FindPatientsByPersonalCodeService findPatientsByPersonalCodeService;

    @Test
    public void testNotValidPersonalCode(){
        FindPatientByPersonalCodeRequest findPatientByPersonalCodeRequest = new FindPatientByPersonalCodeRequest("jokjl");
        CoreError expectedError = new CoreError("Personal data : personal code",
                "Invalid input for personal code!");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        Mockito.when(findPatientByPersonalCodeValidator.validate(findPatientByPersonalCodeRequest)).thenReturn(errors);

        FindPatientByPersonalCodeResponse findPatientByPersonalCodeResponse = findPatientsByPersonalCodeService.execute(findPatientByPersonalCodeRequest);

        assertTrue(findPatientByPersonalCodeResponse.hasErrors());
        assertTrue(findPatientByPersonalCodeResponse.getErrors().size() == 1);
        assertTrue(findPatientByPersonalCodeResponse.getErrors().contains(expectedError));
    }

    @Test
    public void testNoIdInDatabase(){
        FindPatientByPersonalCodeRequest findPatientByPersonalCodeRequest = new FindPatientByPersonalCodeRequest("12345678900");
        CoreError expectedError = new CoreError("database", "Database doesn't contain patient with personal code 12345678900");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        Mockito.when(findPatientByPersonalCodeValidator.validate(findPatientByPersonalCodeRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientDatabase.findPatientsByPersonalCode("12345678900")).thenReturn(Optional.empty());
        FindPatientByPersonalCodeResponse findPatientByPersonalCodeResponse = findPatientsByPersonalCodeService.execute(findPatientByPersonalCodeRequest);

        assertTrue(findPatientByPersonalCodeResponse.hasErrors());
        assertTrue(findPatientByPersonalCodeResponse.getErrors().size() == 1);
        assertTrue(findPatientByPersonalCodeResponse.getErrors().contains(expectedError));
    }

    @Test
    public void testSuccessfullyFound(){
        FindPatientByPersonalCodeRequest findPatientByPersonalCodeRequest = new FindPatientByPersonalCodeRequest("12345678900");

        Mockito.when(findPatientByPersonalCodeValidator.validate(findPatientByPersonalCodeRequest)).thenReturn(new ArrayList<>());
        Patient patient = new Patient(new PersonalData("Name", "Surname", "12345678", "12345678900"));
        Optional<Patient> patientResult = Optional.of(patient);
        Mockito.when(patientDatabase.findPatientsByPersonalCode("12345678900")).thenReturn(patientResult);
        FindPatientByPersonalCodeResponse findPatientByPersonalCodeResponse = findPatientsByPersonalCodeService.execute(findPatientByPersonalCodeRequest);

        assertFalse(findPatientByPersonalCodeResponse.hasErrors());

        assertTrue(findPatientByPersonalCodeResponse.getFoundPatient().getPersonalData().getPersonalCode().equals("12345678900"));
    }
}