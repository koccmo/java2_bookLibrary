package dental_clinic_tests.core.validators_tests.doctor;

import dental_clinic.core.requests.doctor.DeleteDoctorRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.doctor.DeleteDoctorRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class DeleteDoctorValidatorTest {

    DeleteDoctorRequestValidator deleteDoctorRequestValidator = new DeleteDoctorRequestValidator();

    @Test
    public void testNotValidId() {
        DeleteDoctorRequest deleteDoctorRequest = new DeleteDoctorRequest(null);

        CoreError expectedError = new CoreError("id", "Not valid input for id");
        List<CoreError> errorList = deleteDoctorRequestValidator.validate(deleteDoctorRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testNoErrors() {
        DeleteDoctorRequest deleteDoctorRequest = new DeleteDoctorRequest(5L);

        List<CoreError> errorList = deleteDoctorRequestValidator.validate(deleteDoctorRequest);

        assertTrue(errorList.isEmpty());
    }
}
