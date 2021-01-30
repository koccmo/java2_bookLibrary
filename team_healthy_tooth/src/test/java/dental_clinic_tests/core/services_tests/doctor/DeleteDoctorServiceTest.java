package dental_clinic_tests.core.services_tests.doctor;

import dental_clinic.core.requests.doctor.DeleteDoctorRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.doctor.DeleteDoctorResponse;
import dental_clinic.core.services.doctor.DeleteDoctorService;
import dental_clinic.core.validators.doctor.DeleteDoctorRequestValidator;
import dental_clinic.core.database.doctor.DoctorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class DeleteDoctorServiceTest {

    @Mock
    private DeleteDoctorRequestValidator deleteDoctorRequestValidator;
    @Mock
    private DoctorRepository doctorRepository;
    @InjectMocks
    private DeleteDoctorService deleteDoctorService;

    @Test
    public void testNotValidInput() {
        CoreError error = new CoreError("id", "Not valid input for id");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(error);

        DeleteDoctorRequest deleteDoctorRequest = new DeleteDoctorRequest(-9L);
        Mockito.when(deleteDoctorRequestValidator.validate(deleteDoctorRequest)).thenReturn(errorList);

        DeleteDoctorResponse deleteDoctorResponse = deleteDoctorService.execute(deleteDoctorRequest);

        assertTrue(deleteDoctorResponse.hasErrors());
        assertTrue(deleteDoctorResponse.getErrors().size() == 1);
        assertTrue(deleteDoctorResponse.getErrors().contains(error));
    }

    @Test
    public void testNoIdInDatabase() {
        CoreError error = new CoreError("database", "Database doesn't contain id -9");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(error);

        DeleteDoctorRequest deleteDoctorRequest = new DeleteDoctorRequest(-9L);
        Mockito.when(deleteDoctorRequestValidator.validate(deleteDoctorRequest)).thenReturn(new ArrayList<>());
        Mockito.when(doctorRepository.containsId(-9L)).thenReturn(false);

        DeleteDoctorResponse deleteDoctorResponse = deleteDoctorService.execute(deleteDoctorRequest);

        assertTrue(deleteDoctorResponse.hasErrors());
        assertTrue(deleteDoctorResponse.getErrors().size() == 1);
        assertTrue(deleteDoctorResponse.getErrors().contains(error));
    }

    @Test
    public void testDeletedSuccessfully() {
        DeleteDoctorRequest deleteDoctorRequest = new DeleteDoctorRequest(9L);
        Mockito.when(deleteDoctorRequestValidator.validate(deleteDoctorRequest)).thenReturn(new ArrayList<>());
        Mockito.when(doctorRepository.containsId(9L)).thenReturn(true);

        DeleteDoctorResponse deleteDoctorResponse = deleteDoctorService.execute(deleteDoctorRequest);

        assertFalse(deleteDoctorResponse.hasErrors());
        assertTrue(deleteDoctorResponse.getId().equals(9L));
    }

}
