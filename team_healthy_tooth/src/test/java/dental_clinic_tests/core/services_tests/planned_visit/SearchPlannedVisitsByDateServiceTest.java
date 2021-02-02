package dental_clinic_tests.core.services_tests.planned_visit;
/*
import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.core.requests.plannedVisit.SearchPlannedVisitsByDateRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.SearchPlannedVisitsByDateResponse;
import dental_clinic.core.services.planned_visit.SearchPlannedVisitsByDateService;
import dental_clinic.core.validators.planned_visit.SearchPlannedVisitsByDateRequestValidator;
import dental_clinic.core.database.planned_visit.PlannedVisitsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchPlannedVisitsByDateServiceTest {

    @Mock
    private SearchPlannedVisitsByDateRequestValidator searchPlannedVisitsByDateRequestValidator;
    @Mock
    private PlannedVisitsRepository plannedVisitsRepository;
    @InjectMocks
    private SearchPlannedVisitsByDateService searchPlannedVisitsByDateService;

    @Test
    public void testValidationError() {
        SearchPlannedVisitsByDateRequest searchPlannedVisitsByDateRequest = new SearchPlannedVisitsByDateRequest(0, 2, 12, 13);

        List<CoreError> errorList = new ArrayList<>();
        CoreError expectedError = new CoreError("date", "Not valid input for date for search");
        errorList.add(expectedError);

        Mockito.when(searchPlannedVisitsByDateRequestValidator.validate(searchPlannedVisitsByDateRequest)).thenReturn(errorList);

        SearchPlannedVisitsByDateResponse searchPlannedVisitsByDateResponse = searchPlannedVisitsByDateService.execute(searchPlannedVisitsByDateRequest);

        assertTrue(searchPlannedVisitsByDateResponse.hasErrors());
        assertTrue(searchPlannedVisitsByDateResponse.getErrors().size() == 1);
        assertTrue(searchPlannedVisitsByDateResponse.getErrors().contains(expectedError));
    }

    @Test
    public void testEmptyDatabase() {
        SearchPlannedVisitsByDateRequest searchPlannedVisitsByDateRequest = new SearchPlannedVisitsByDateRequest(1, 2, 11, 11);

        List<CoreError> errorList = new ArrayList<>();
        CoreError expectedError = new CoreError("database", "Database is empty");
        errorList.add(expectedError);

        Mockito.when(searchPlannedVisitsByDateRequestValidator.validate(searchPlannedVisitsByDateRequest)).thenReturn(new ArrayList<>());
        Mockito.when(plannedVisitsRepository.searchPlannedVisitsByDate(1, 2, 11, 11)).thenReturn(new ArrayList<>());

        SearchPlannedVisitsByDateResponse searchPlannedVisitsByDateResponse = searchPlannedVisitsByDateService.execute(searchPlannedVisitsByDateRequest);

        assertTrue(searchPlannedVisitsByDateResponse.hasErrors());
        assertTrue(searchPlannedVisitsByDateResponse.getErrors().size() == 1);
        assertTrue(searchPlannedVisitsByDateResponse.getErrors().contains(expectedError));
    }

    @Test
    public void testSearchCompleted() {
        SearchPlannedVisitsByDateRequest searchPlannedVisitsByDateRequest = new SearchPlannedVisitsByDateRequest(1, 4, 11, 12);

        List<PlannedVisit> plannedVisitList = new ArrayList<>();
        PersonalData validPersonalData = new PersonalData("Name", "Surname", "12345678", "01012547896");
        GregorianCalendar gregorianCalendar = new GregorianCalendar(2021, Calendar.DECEMBER, 03, 15, 30);
        Doctor doctor = new Doctor("Bobik", "Shpric", "12345678");
        PlannedVisit plannedVisit = new PlannedVisit(gregorianCalendar, validPersonalData, doctor);
        plannedVisitList.add(plannedVisit);

        Mockito.when(searchPlannedVisitsByDateRequestValidator.validate(searchPlannedVisitsByDateRequest)).thenReturn(new ArrayList<>());
        Mockito.when(plannedVisitsRepository.searchPlannedVisitsByDate(1, 4, 11, 12)).thenReturn(plannedVisitList);

        SearchPlannedVisitsByDateResponse searchPlannedVisitsByDateResponse = searchPlannedVisitsByDateService.execute(searchPlannedVisitsByDateRequest);

        assertFalse(searchPlannedVisitsByDateResponse.hasErrors());
        assertTrue(searchPlannedVisitsByDateResponse.getPlannedVisitList().size() == 1);
    }
}*/