package dental_clinic_tests.core.validators_tests.patient;

import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.requests.patient.UpdatePatientsJowlInfoRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.patient.UpdatePatientJowlInfoRequestValidator;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
import static org.junit.Assert.*;

public class UpdatePatientJowlInfoRequestValidatorTest {

    UpdatePatientJowlInfoRequestValidator updatePatientJowlInfoRequestValidator = new UpdatePatientJowlInfoRequestValidator();

    @Test
    public void testNotValidId() {

        CoreError expectedError = new CoreError("id", "Not valid input of id");
        Map<Integer, ToothStatus> jowlInfo = new HashMap<>();
        jowlInfo.put(11, ToothStatus.FASETE);
        UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest = new UpdatePatientsJowlInfoRequest(-8L, jowlInfo);
        List<CoreError> coreErrors = updatePatientJowlInfoRequestValidator.validate(updatePatientsJowlInfoRequest);

        assertTrue(coreErrors.size() == 1);
        assertTrue(coreErrors.contains(expectedError));
    }

    @Test
    public void testToothNumberError() {

        CoreError expectedError = new CoreError("tooth number", "Not valid tooth number 1");
        Map<Integer, ToothStatus> jowlInfo = new HashMap<>();
        jowlInfo.put(1, ToothStatus.FASETE);
        UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest = new UpdatePatientsJowlInfoRequest(1L, jowlInfo);
        List<CoreError> coreErrors = updatePatientJowlInfoRequestValidator.validate(updatePatientsJowlInfoRequest);

        assertTrue(coreErrors.size() == 1);
        assertTrue(coreErrors.contains(expectedError));
    }

    @Test
    public void testToothStatusError() {

        CoreError expectedError = new CoreError("tooth status", "Not valid tooth status for tooth number 11");
        Map<Integer, ToothStatus> jowlInfo = new HashMap<>();
        jowlInfo.put(11, ToothStatus.OTHER);
        UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest = new UpdatePatientsJowlInfoRequest(1L, jowlInfo);
        List<CoreError> coreErrors = updatePatientJowlInfoRequestValidator.validate(updatePatientsJowlInfoRequest);

        assertTrue(coreErrors.size() == 1);
        assertTrue(coreErrors.contains(expectedError));
    }

    @Test
    public void testToothStatusAndToothNumberError() {

        CoreError expectedError1 = new CoreError("tooth status", "Not valid tooth status for tooth number 1");
        CoreError expectedError2 = new CoreError("tooth number", "Not valid tooth number 1");
        Map<Integer, ToothStatus> jowlInfo = new HashMap<>();
        jowlInfo.put(1, ToothStatus.OTHER);
        UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest = new UpdatePatientsJowlInfoRequest(1L, jowlInfo);
        List<CoreError> coreErrors = updatePatientJowlInfoRequestValidator.validate(updatePatientsJowlInfoRequest);

        assertTrue(coreErrors.size() == 2);
        assertTrue(coreErrors.contains(expectedError1));
        assertTrue(coreErrors.contains(expectedError2));
    }

    @Test
    public void testToothStatusAndToothNumberError2() {

        CoreError expectedError1 = new CoreError("tooth status", "Not valid tooth status for tooth number 1");
        CoreError expectedError2 = new CoreError("tooth status", "Not valid tooth status for tooth number 1");
        CoreError expectedError3 = new CoreError("tooth number", "Not valid tooth number 2");
        CoreError expectedError4 = new CoreError("tooth number", "Not valid tooth number 2");
        Map<Integer, ToothStatus> jowlInfo = new HashMap<>();
        jowlInfo.put(1, ToothStatus.OTHER);
        jowlInfo.put(2, ToothStatus.OTHER);
        UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest = new UpdatePatientsJowlInfoRequest(1L, jowlInfo);
        List<CoreError> coreErrors = updatePatientJowlInfoRequestValidator.validate(updatePatientsJowlInfoRequest);

        assertTrue(coreErrors.size() == 4);
        assertTrue(coreErrors.contains(expectedError1));
        assertTrue(coreErrors.contains(expectedError2));
        assertTrue(coreErrors.contains(expectedError3));
        assertTrue(coreErrors.contains(expectedError4));
    }

    @Test
    public void testValidInput() {

        Map<Integer, ToothStatus> jowlInfo = new HashMap<>();
        jowlInfo.put(11, ToothStatus.NAV_ZOBA);
        jowlInfo.put(85, ToothStatus.PLAST_KRONITIS);
        UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest = new UpdatePatientsJowlInfoRequest(1L, jowlInfo);
        List<CoreError> coreErrors = updatePatientJowlInfoRequestValidator.validate(updatePatientsJowlInfoRequest);

        assertTrue(coreErrors.isEmpty());
    }
}*/