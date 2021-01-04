package dental_clinic_tests.core.validators_tests.planned_visit;

import dental_clinic.core.requests.plannedVisit.ChangePlannedVisitTimeRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.planned_visit.ChangePlannedVisitTimeRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ChangePlannedVisitTimeRequestValidatorTest {

    ChangePlannedVisitTimeRequestValidator changePlannedVisitTimeRequestValidator = new ChangePlannedVisitTimeRequestValidator();

    @Test
    public void testNotValidId() {
        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(null, "25-05-2021 15:30");
        List<CoreError> errors = changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest);

        CoreError expectedError = new CoreError("id", "Not valid input for id");

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidTimeFormat1() {
        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "25 05 2021 15:30");
        List<CoreError> errors = changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest);

        CoreError expectedError = new CoreError("date", "Not valid input for date");

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidTimeFormat2() {
        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "25:05:2021 15:30");
        List<CoreError> errors = changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest);

        CoreError expectedError = new CoreError("date", "Not valid input for date");

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidTimeFormat3() {
        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "25 May 2021 15:30");
        List<CoreError> errors = changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest);

        CoreError expectedError = new CoreError("date", "Not valid input for date");

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidTimeFormat4() {
        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "25-05-2021 15_30");
        List<CoreError> errors = changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest);

        CoreError expectedError = new CoreError("date", "Not valid input for date");

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidTimeFormat5() {
        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "25-05-2021 15:3");
        List<CoreError> errors = changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest);

        CoreError expectedError = new CoreError("date", "Not valid input for date");

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidTimeFormat6() {
        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "25-05-2021");
        List<CoreError> errors = changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest);

        CoreError expectedError = new CoreError("date", "Not valid input for date");

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidTimeFormat7() {
        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "15:30");
        List<CoreError> errors = changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest);

        CoreError expectedError = new CoreError("date", "Not valid input for date");

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidTimeFormat8() {
        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "");
        List<CoreError> errors = changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest);

        CoreError expectedError = new CoreError("date", "Not valid input for date");

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidTimeFormat9() {
        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "30-02-2021 15:30");
        List<CoreError> errors = changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest);

        CoreError expectedError = new CoreError("date", "Not valid input for date");

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidTimeFormat10() {
        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "-2-02-2021 15:30");
        List<CoreError> errors = changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest);

        CoreError expectedError = new CoreError("date", "Not valid input for date");

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidTimeFormat11() {
        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "02-02-2021 15:78");
        List<CoreError> errors = changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest);

        CoreError expectedError = new CoreError("date", "Not valid input for date");

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testNotValidTimeFormat12() {
        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "02-02-2021 25:08");
        List<CoreError> errors = changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest);

        CoreError expectedError = new CoreError("date", "Not valid input for date");

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidInput() {
        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "02-02-2021 15:08");
        List<CoreError> errors = changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest);

        assertTrue(errors.isEmpty());
    }
}