package dental_clinic_tests.core.validators_tests.doctor;

import dental_clinic.core.requests.doctor.FillDoctorsWorkGraphicRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.doctor.FillDoctorsWorkGraphicRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FillDoctorsWorkGraphicRequestValidatorTest {

    FillDoctorsWorkGraphicRequestValidator fillDoctorsWorkGraphicRequestValidator = new FillDoctorsWorkGraphicRequestValidator();

    @Test
    public void testIdError() {
        FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest = new FillDoctorsWorkGraphicRequest(-8L, 1, "10:00", "12:00");
        CoreError coreError = new CoreError("id", "Not valid input for id");

        List<CoreError> errorList = fillDoctorsWorkGraphicRequestValidator.validate(fillDoctorsWorkGraphicRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(coreError));
    }

    @Test
    public void testWorkGraphicError1() {
        FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest = new FillDoctorsWorkGraphicRequest(8L, 1, "10 00", "12:00");
        CoreError coreError = new CoreError("time", "Not valid input for time");

        List<CoreError> errorList = fillDoctorsWorkGraphicRequestValidator.validate(fillDoctorsWorkGraphicRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(coreError));
    }

    @Test
    public void testWorkGraphicError2() {
        FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest = new FillDoctorsWorkGraphicRequest(8L, 1, "25:00", "12:00");
        CoreError coreError = new CoreError("time", "Not valid input for time");

        List<CoreError> errorList = fillDoctorsWorkGraphicRequestValidator.validate(fillDoctorsWorkGraphicRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(coreError));
    }

    @Test
    public void testWorkGraphicError3() {
        FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest = new FillDoctorsWorkGraphicRequest(8L, 1, "24:00", "05.05.2021 12:00");
        CoreError coreError = new CoreError("time", "Not valid input for time");

        List<CoreError> errorList = fillDoctorsWorkGraphicRequestValidator.validate(fillDoctorsWorkGraphicRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(coreError));
    }

    @Test
    public void testWorkGraphicError4() {
        FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest = new FillDoctorsWorkGraphicRequest(8L, 1, "78:00", "05.05.2021 12:00");
        CoreError coreError = new CoreError("time", "Not valid input for time");

        List<CoreError> errorList = fillDoctorsWorkGraphicRequestValidator.validate(fillDoctorsWorkGraphicRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(coreError));
    }

    @Test
    public void testWorkGraphicAndIdError() {
        FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest = new FillDoctorsWorkGraphicRequest(-8L, 1, "78:00", "05.05.2021 12:00");
        CoreError coreError1 = new CoreError("time", "Not valid input for time");
        CoreError coreError2 = new CoreError("id", "Not valid input for id");
        List<CoreError> errorList = fillDoctorsWorkGraphicRequestValidator.validate(fillDoctorsWorkGraphicRequest);

        assertTrue(errorList.size() == 2);
        assertTrue(errorList.contains(coreError1));
        assertTrue(errorList.contains(coreError2));
    }

    @Test
    public void testValidInput() {
        FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest = new FillDoctorsWorkGraphicRequest(8L, 1, "10:00", "12:00");

        List<CoreError> errorList = fillDoctorsWorkGraphicRequestValidator.validate(fillDoctorsWorkGraphicRequest);

        assertTrue(errorList.isEmpty());
    }

}