package dental_clinic_tests.core.services_tests.planned_visit;

import dental_clinic.core.requests.plannedVisit.CancelPlannedVisitRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.CancelPlannedVisitResponse;
import dental_clinic.core.services.planned_visit.CancelPlannedVisitService;
import dental_clinic.core.validators.planned_visit.CancelPlannedVisitValidator;
import dental_clinic.core.database.planned_visit.PlannedVisitsRepository;
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
public class CancelPlannedVisitServiceTest {

    @Mock
    private PlannedVisitsRepository plannedVisitsRepository;
    @Mock
    private CancelPlannedVisitValidator cancelPlannedVisitValidator;
    @InjectMocks
    private CancelPlannedVisitService cancelPlannedVisitService;

    @Test
    public void validationErrors() {
        CoreError coreError = new CoreError("id", "Not valid input for id");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(coreError);

        CancelPlannedVisitRequest cancelPlannedVisitRequest = new CancelPlannedVisitRequest(-5L);

        Mockito.when(cancelPlannedVisitValidator.validate(cancelPlannedVisitRequest)).thenReturn(errorList);

        CancelPlannedVisitResponse cancelPlannedVisitResponse = cancelPlannedVisitService.execute(cancelPlannedVisitRequest);

        assertTrue(cancelPlannedVisitResponse.hasErrors());
        assertTrue(cancelPlannedVisitResponse.getErrors().size() == 1);
        assertTrue(cancelPlannedVisitResponse.getErrors().contains(coreError));
        Mockito.verifyNoInteractions(plannedVisitsRepository);
    }

    @Test
    public void testDatabaseDoesNotContainId() {
        CoreError coreError = new CoreError("database", "Database doesn't contain id 2");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(coreError);

        CancelPlannedVisitRequest cancelPlannedVisitRequest = new CancelPlannedVisitRequest(2L);

        Mockito.when(cancelPlannedVisitValidator.validate(cancelPlannedVisitRequest)).thenReturn(new ArrayList<>());
        Mockito.when(plannedVisitsRepository.containsId(2L)).thenReturn(false);

        CancelPlannedVisitResponse cancelPlannedVisitResponse = cancelPlannedVisitService.execute(cancelPlannedVisitRequest);

        assertTrue(cancelPlannedVisitResponse.hasErrors());
        assertTrue(cancelPlannedVisitResponse.getErrors().size() == 1);
        assertTrue(cancelPlannedVisitResponse.getErrors().contains(coreError));
    }

    @Test
    public void testSuccessfullyAdded() {
        CancelPlannedVisitRequest cancelPlannedVisitRequest = new CancelPlannedVisitRequest(2L);

        Mockito.when(cancelPlannedVisitValidator.validate(cancelPlannedVisitRequest)).thenReturn(new ArrayList<>());
        Mockito.when(plannedVisitsRepository.containsId(2L)).thenReturn(true);

        CancelPlannedVisitResponse cancelPlannedVisitResponse = cancelPlannedVisitService.execute(cancelPlannedVisitRequest);

        assertFalse(cancelPlannedVisitResponse.hasErrors());
        assertTrue(cancelPlannedVisitResponse.getId().equals(2L));
    }

}