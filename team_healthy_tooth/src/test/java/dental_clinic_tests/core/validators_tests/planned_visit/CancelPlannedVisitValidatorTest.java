package dental_clinic_tests.core.validators_tests.planned_visit;

import dental_clinic.core.requests.plannedVisit.CancelPlannedVisitRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.planned_visit.CancelPlannedVisitValidator;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class CancelPlannedVisitValidatorTest {

    CancelPlannedVisitValidator cancelPlannedVisitValidator = new CancelPlannedVisitValidator();

    @Test
    public void testNotValidId() {
        CancelPlannedVisitRequest cancelPlannedVisitRequest = new CancelPlannedVisitRequest(-8L);
        List<CoreError> errors = cancelPlannedVisitValidator.validate(cancelPlannedVisitRequest);
        CoreError coreError = new CoreError("id", "Not valid input for id");
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(coreError));
    }

    @Test
    public void testVisitCancelled() {
        CancelPlannedVisitRequest cancelPlannedVisitRequest = new CancelPlannedVisitRequest(1L);
        List<CoreError> errors = cancelPlannedVisitValidator.validate(cancelPlannedVisitRequest);
        assertTrue(errors.isEmpty());
    }

}