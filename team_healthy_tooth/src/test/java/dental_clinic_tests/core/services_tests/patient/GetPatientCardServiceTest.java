package dental_clinic_tests.core.services_tests.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.patient.GetPatientCardRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.GetPatientCardResponse;
import dental_clinic.core.services.patient.GetPatientCardService;
import dental_clinic.core.validators.patient.GetPatientCardRequestValidator;
import dental_clinic.core.database.patient.PatientRepository;
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
public class GetPatientCardServiceTest {

    @Mock
    private GetPatientCardRequestValidator getPatientCardRequestValidator;
    @Mock
    private PatientRepository patientRepository;
    @InjectMocks
    private GetPatientCardService getPatientCardService;

    @Test
    public void testNotValidId(){
        GetPatientCardRequest getPatientCardRequest = new GetPatientCardRequest(null);

        CoreError error = new CoreError("id", "Not valid input for id");
        List<CoreError> errors = new ArrayList<>();
        errors.add(error);

        Mockito.when(getPatientCardRequestValidator.validate(getPatientCardRequest)).thenReturn(errors);

        GetPatientCardResponse getPatientCardResponse = getPatientCardService.execute(getPatientCardRequest);

        assertTrue(getPatientCardResponse.hasErrors());
        assertTrue(getPatientCardResponse.getErrors().size() == 1);
        assertTrue(getPatientCardResponse.getErrors().contains(error));
    }

    @Test
    public void testNoIdInDatabase(){
        GetPatientCardRequest getPatientCardRequest = new GetPatientCardRequest(5L);

        CoreError error = new CoreError("database", "Database doesn't contain patient with id 5");
        List<CoreError> errors = new ArrayList<>();
        errors.add(error);

        Mockito.when(getPatientCardRequestValidator.validate(getPatientCardRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.containsPatientWithSpecificId(getPatientCardRequest.getId())).thenReturn(false);
        GetPatientCardResponse getPatientCardResponse = getPatientCardService.execute(getPatientCardRequest);

        assertTrue(getPatientCardResponse.hasErrors());
        assertTrue(getPatientCardResponse.getErrors().size() == 1);
        assertTrue(getPatientCardResponse.getErrors().contains(error));
    }

    @Test
    public void testSuccessfullyFound(){
        GetPatientCardRequest getPatientCardRequest = new GetPatientCardRequest(5L);
        PersonalData personalData = new PersonalData("Name", "Surname", "12345678", "25052512345");
        Patient patientCard = new Patient(personalData);

        Mockito.when(getPatientCardRequestValidator.validate(getPatientCardRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.containsPatientWithSpecificId(getPatientCardRequest.getId())).thenReturn(true);
        Mockito.when(patientRepository.getPatientCard(getPatientCardRequest.getId())).thenReturn(Optional.of(patientCard));
        GetPatientCardResponse getPatientCardResponse = getPatientCardService.execute(getPatientCardRequest);

        assertFalse(getPatientCardResponse.hasErrors());
        assertTrue(getPatientCardResponse.getPatient().equals(patientCard));
    }
}