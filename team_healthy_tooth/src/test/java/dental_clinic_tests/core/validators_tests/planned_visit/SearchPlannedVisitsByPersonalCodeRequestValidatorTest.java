package dental_clinic_tests.core.validators_tests.planned_visit;

import dental_clinic.core.requests.plannedVisit.SearchPlannedVisitsByPersonalCodeRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.planned_visit.SearchPlannedVisitsByPersonalCodeRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchPlannedVisitsByPersonalCodeRequestValidatorTest {

    SearchPlannedVisitsByPersonalCodeRequestValidator searchPlannedVisitsByPersonalCodeRequestValidator = new SearchPlannedVisitsByPersonalCodeRequestValidator();

    @Test
    public void testEmptyPersonalCode() {
        SearchPlannedVisitsByPersonalCodeRequest searchPlannedVisitsByPersonalCodeRequest = new SearchPlannedVisitsByPersonalCodeRequest("");
        List<CoreError> errorList = searchPlannedVisitsByPersonalCodeRequestValidator.validate(searchPlannedVisitsByPersonalCodeRequest);
        CoreError expectedError = new CoreError("personal code", "Personal code can't be empty");

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testPersonalCodeNotValidFormat() {
        SearchPlannedVisitsByPersonalCodeRequest searchPlannedVisitsByPersonalCodeRequest = new SearchPlannedVisitsByPersonalCodeRequest("350147856");
        List<CoreError> errorList = searchPlannedVisitsByPersonalCodeRequestValidator.validate(searchPlannedVisitsByPersonalCodeRequest);
        CoreError expectedError = new CoreError("Personal data : personal code", "Valid personal format is DDMMYYNNNNN or DDMMYY-NNNNN, where N is digit");

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testValidInput() {
        SearchPlannedVisitsByPersonalCodeRequest searchPlannedVisitsByPersonalCodeRequest = new SearchPlannedVisitsByPersonalCodeRequest("01011478965");
        List<CoreError> errorList = searchPlannedVisitsByPersonalCodeRequestValidator.validate(searchPlannedVisitsByPersonalCodeRequest);

        assertTrue(errorList.isEmpty());
    }

}