package dental_clinic_tests.core.services_tests.patient;

import dental_clinic.core.requests.ContainsDatabaseIdRequest;
import dental_clinic.core.responses.ContainsDatabaseIdResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.services.ContainsDatabaseIdService;
import dental_clinic.core.validators.ContainsDatabaseIdValidator;
import dental_clinic.core.database.patient.PatientRepository;
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
public class ContainsDatabaseIdServiceTest {

    @Mock
    private PatientRepository patientRepository;
    @Mock
    private ContainsDatabaseIdValidator containsDatabaseIdValidator;
    @InjectMocks
    private ContainsDatabaseIdService containsDatabaseIdService;

    @Test
    public void testNotValidId(){
        ContainsDatabaseIdRequest containsDatabaseIdRequest = new ContainsDatabaseIdRequest(-7L);

        CoreError expectedError = new CoreError("id", "Invalid input patient ID!");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        Mockito.when(containsDatabaseIdValidator.validate(containsDatabaseIdRequest)).thenReturn(errors);
        ContainsDatabaseIdResponse containsDatabaseIdResponse = containsDatabaseIdService.execute(containsDatabaseIdRequest);

        assertTrue(containsDatabaseIdResponse.hasErrors());
        assertTrue(containsDatabaseIdResponse.getErrors().size() == 1);
        assertTrue(containsDatabaseIdResponse.getErrors().contains(expectedError));
    }

    @Test
    public void testNoIdInDatabase(){
        ContainsDatabaseIdRequest containsDatabaseIdRequest = new ContainsDatabaseIdRequest(7L);

        CoreError expectedError = new CoreError("id", "Database doesn't contain patient with id");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        Mockito.when(containsDatabaseIdValidator.validate(containsDatabaseIdRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.containsPatientWithSpecificId(7L)).thenReturn(false);
        ContainsDatabaseIdResponse containsDatabaseIdResponse = containsDatabaseIdService.execute(containsDatabaseIdRequest);

        assertTrue(containsDatabaseIdResponse.hasErrors());
        assertTrue(containsDatabaseIdResponse.getErrors().size() == 1);
        assertTrue(containsDatabaseIdResponse.getErrors().contains(expectedError));
    }

    @Test
    public void testIsIdInDatabase(){
        ContainsDatabaseIdRequest containsDatabaseIdRequest = new ContainsDatabaseIdRequest(7L);

        Mockito.when(containsDatabaseIdValidator.validate(containsDatabaseIdRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.containsPatientWithSpecificId(7L)).thenReturn(true);
        ContainsDatabaseIdResponse containsDatabaseIdResponse = containsDatabaseIdService.execute(containsDatabaseIdRequest);

        assertTrue(containsDatabaseIdResponse.getId().equals(7L));
    }
}