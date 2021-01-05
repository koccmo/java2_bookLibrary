package dental_clinic_tests.core.validators_tests.planned_visit;

import dental_clinic.core.requests.plannedVisit.SearchPlannedVisitsByDateRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.planned_visit.SearchPlannedVisitsByDateRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchPlannedVisitsByDateRequestValidatorTest {

    SearchPlannedVisitsByDateRequestValidator searchPlannedVisitsByDateRequestValidator = new SearchPlannedVisitsByDateRequestValidator();

    @Test
    public void testNegativeInput() {
        CoreError coreError = new CoreError("date", "Not valid input for date for search");
        SearchPlannedVisitsByDateRequest searchPlannedVisitsByDateRequest
                = new SearchPlannedVisitsByDateRequest(-5, 8, 8, 12);
        List<CoreError> errorList = searchPlannedVisitsByDateRequestValidator.validate(searchPlannedVisitsByDateRequest);
        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(coreError));
    }

    @Test
    public void testNegativeInput2() {
        CoreError coreError = new CoreError("date", "Not valid input for date for search");
        SearchPlannedVisitsByDateRequest searchPlannedVisitsByDateRequest
                = new SearchPlannedVisitsByDateRequest(55, 8, 13, 12);
        List<CoreError> errorList = searchPlannedVisitsByDateRequestValidator.validate(searchPlannedVisitsByDateRequest);
        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(coreError));
    }

    @Test
    public void testValidInput() {
        SearchPlannedVisitsByDateRequest searchPlannedVisitsByDateRequest
                = new SearchPlannedVisitsByDateRequest(15, 18, 11, 12);
        List<CoreError> errorList = searchPlannedVisitsByDateRequestValidator.validate(searchPlannedVisitsByDateRequest);
        assertTrue(errorList.isEmpty());
    }
}