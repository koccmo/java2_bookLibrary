package dental_clinic_tests.core.services_tests.doctor;

import dental_clinic.core.requests.doctor.FillDoctorsWorkGraphicRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.doctor.FillDoctorsWorkGraphicResponse;
import dental_clinic.core.services.doctor.FillDoctorsWorkGraphicService;
import dental_clinic.core.validators.doctor.FillDoctorsWorkGraphicRequestValidator;
import dental_clinic.core.database.doctor.DoctorRepository;
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
public class FillDoctorsWorkGraphicServiceTest {

    @Mock
    private FillDoctorsWorkGraphicRequestValidator fillDoctorsWorkGraphicRequestValidator;
    @Mock
    private DoctorRepository doctorRepository;
    @InjectMocks
    private FillDoctorsWorkGraphicService fillDoctorsWorkGraphicService;

    @Test
    public void testValidationError() {
        CoreError expectedError = new CoreError("id", "Not valid input for id");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(expectedError);

        FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest = new FillDoctorsWorkGraphicRequest(-8L, 5, "10:00", "15:00");

        Mockito.when(fillDoctorsWorkGraphicRequestValidator.validate(fillDoctorsWorkGraphicRequest)).thenReturn(errorList);

        FillDoctorsWorkGraphicResponse fillDoctorsWorkGraphicResponse = fillDoctorsWorkGraphicService.execute(fillDoctorsWorkGraphicRequest);

        assertTrue(fillDoctorsWorkGraphicResponse.hasErrors());
        assertTrue(fillDoctorsWorkGraphicResponse.getErrors().contains(expectedError));
    }

    @Test
    public void testDatabaseDoesNotContainId() {
        CoreError expectedError = new CoreError("database", "Database doesn't contain id 5");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(expectedError);

        FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest = new FillDoctorsWorkGraphicRequest(5L, 5, "10:00", "15:00");

        Mockito.when(fillDoctorsWorkGraphicRequestValidator.validate(fillDoctorsWorkGraphicRequest)).thenReturn(new ArrayList<>());
        Mockito.when(doctorRepository.containsId(5L)).thenReturn(false);

        FillDoctorsWorkGraphicResponse fillDoctorsWorkGraphicResponse = fillDoctorsWorkGraphicService.execute(fillDoctorsWorkGraphicRequest);

        assertTrue(fillDoctorsWorkGraphicResponse.hasErrors());
        assertTrue(fillDoctorsWorkGraphicResponse.getErrors().contains(expectedError));
    }

    @Test
    public void testNotValidTimeLogic() {
        CoreError expectedError = new CoreError("time", "Not valid time");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(expectedError);

        FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest = new FillDoctorsWorkGraphicRequest(5L, 5, "17:00", "15:00");

        Mockito.when(fillDoctorsWorkGraphicRequestValidator.validate(fillDoctorsWorkGraphicRequest)).thenReturn(new ArrayList<>());
        Mockito.when(doctorRepository.containsId(5L)).thenReturn(true);

        FillDoctorsWorkGraphicResponse fillDoctorsWorkGraphicResponse = fillDoctorsWorkGraphicService.execute(fillDoctorsWorkGraphicRequest);

        assertTrue(fillDoctorsWorkGraphicResponse.hasErrors());
        assertTrue(fillDoctorsWorkGraphicResponse.getErrors().contains(expectedError));
    }

    @Test
    public void testSuccessfulCase() {

        FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest = new FillDoctorsWorkGraphicRequest(5L, 5, "12:00", "15:00");

        Mockito.when(fillDoctorsWorkGraphicRequestValidator.validate(fillDoctorsWorkGraphicRequest)).thenReturn(new ArrayList<>());
        Mockito.when(doctorRepository.containsId(5L)).thenReturn(true);

        FillDoctorsWorkGraphicResponse fillDoctorsWorkGraphicResponse = fillDoctorsWorkGraphicService.execute(fillDoctorsWorkGraphicRequest);

        assertFalse(fillDoctorsWorkGraphicResponse.hasErrors());
        assertTrue(fillDoctorsWorkGraphicResponse.getId().equals(5L));
    }

}