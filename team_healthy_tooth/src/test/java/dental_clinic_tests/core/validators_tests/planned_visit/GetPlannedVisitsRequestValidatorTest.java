package dental_clinic_tests.core.validators_tests.planned_visit;

import dental_clinic.core.requests.plannedVisit.GetPlannedVisitsRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.planned_visit.GetPlannedVisitsRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GetPlannedVisitsRequestValidatorTest {

    GetPlannedVisitsRequestValidator getPlannedVisitsRequestValidator = new GetPlannedVisitsRequestValidator();

    @Test
    public void testOK() {
        GetPlannedVisitsRequest getPlannedVisitsRequest = new GetPlannedVisitsRequest();
        List<CoreError> errorList = getPlannedVisitsRequestValidator.validate(getPlannedVisitsRequest);

        assertTrue(errorList.isEmpty());
     }

}