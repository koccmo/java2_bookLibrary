package dental_clinic_tests.core.services_tests.planned_visit;

import dental_clinic.core.requests.plannedVisit.ChangePlannedVisitTimeRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.ChangePlannedVisitTimeResponse;
import dental_clinic.core.services.planned_visit.ChangePlannedVisitTimeService;
import dental_clinic.core.validators.planned_visit.ChangePlannedVisitTimeRequestValidator;
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
public class ChangePlannedVisitTimeServiceTest {

    @Mock
    private PlannedVisitsRepository plannedVisitsRepository;
    @Mock
    private ChangePlannedVisitTimeRequestValidator changePlannedVisitTimeRequestValidator;
    @InjectMocks
    private ChangePlannedVisitTimeService changePlannedVisitTimeService;

    @Test
    public void testValidationErrors() {
        CoreError coreError = new CoreError("date", "Not valid input for date");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(coreError);

        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "25-01 14:25");

        Mockito.when(changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest)).thenReturn(errorList);

        ChangePlannedVisitTimeResponse changePlannedVisitTimeResponse = changePlannedVisitTimeService.execute(changePlannedVisitTimeRequest);

        assertTrue(changePlannedVisitTimeResponse.hasErrors());
        assertTrue(changePlannedVisitTimeResponse.getErrors().size() == 1);
        assertTrue(changePlannedVisitTimeResponse.getErrors().contains(coreError));
    }

    @Test
    public void testDatabaseDoesNotContainId() {
        CoreError coreError = new CoreError("database", "Database doesn't contain id 2");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(coreError);

        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(2L, "25-01-2022 14:25");

        Mockito.when(changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest)).thenReturn(new ArrayList<>());
        Mockito.when(plannedVisitsRepository.containsId(2L)).thenReturn(false);

        ChangePlannedVisitTimeResponse changePlannedVisitTimeResponse = changePlannedVisitTimeService.execute(changePlannedVisitTimeRequest);
        changePlannedVisitTimeResponse.getErrors().forEach(System.out::println);
        assertTrue(changePlannedVisitTimeResponse.hasErrors());
        assertTrue(changePlannedVisitTimeResponse.getErrors().size() == 1);
        assertTrue(changePlannedVisitTimeResponse.getErrors().contains(coreError));
    }

    @Test
    public void testVisitTimeInPast() {
        CoreError coreError = new CoreError("date", "Visit date must be in future");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(coreError);

        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "01-01-2021 14:25");

        Mockito.when(changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest)).thenReturn(new ArrayList<>());
        Mockito.when(plannedVisitsRepository.containsId(1L)).thenReturn(true);

        ChangePlannedVisitTimeResponse changePlannedVisitTimeResponse = changePlannedVisitTimeService.execute(changePlannedVisitTimeRequest);

        assertTrue(changePlannedVisitTimeResponse.hasErrors());
        assertTrue(changePlannedVisitTimeResponse.getErrors().size() == 1);
        assertTrue(changePlannedVisitTimeResponse.getErrors().contains(coreError));
    }

    @Test
    public void testChangedSuccessfully() {
        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(1L, "01-05-2021 14:25");

        Mockito.when(changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest)).thenReturn(new ArrayList<>());
        Mockito.when(plannedVisitsRepository.containsId(1L)).thenReturn(true);

        ChangePlannedVisitTimeResponse changePlannedVisitTimeResponse = changePlannedVisitTimeService.execute(changePlannedVisitTimeRequest);

        assertFalse(changePlannedVisitTimeResponse.hasErrors());
    }
}