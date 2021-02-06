package dental_clinic_tests.core.services_tests.planned_visit;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.core.requests.plannedVisit.SearchPlannedVisitsByPersonalCodeRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.SearchPlannedVisitsByPersonalCodeResponse;
import dental_clinic.core.services.planned_visit.SearchPlannedVisitsByPersonalCodeService;
import dental_clinic.core.validators.planned_visit.SearchPlannedVisitsByPersonalCodeRequestValidator;
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
/*
@RunWith(MockitoJUnitRunner.class)
public class SearchPlannedVisitsByPersonalCodeServiceTest {

    @Mock
    private SearchPlannedVisitsByPersonalCodeRequestValidator searchPlannedVisitsByPersonalCodeRequestValidator;
    @Mock
    private PlannedVisitsRepository plannedVisitsRepository;
    @InjectMocks
    private SearchPlannedVisitsByPersonalCodeService searchPlannedVisitsByPersonalCodeService;

    @Test
    public void testValidationErrors() {
        SearchPlannedVisitsByPersonalCodeRequest searchPlannedVisitsByPersonalCodeRequest = new SearchPlannedVisitsByPersonalCodeRequest(null);
        List<CoreError> errorList = new ArrayList<>();
        CoreError error = new CoreError("personal code", "Personal code can't be empty");
        errorList.add(error);

        Mockito.when(searchPlannedVisitsByPersonalCodeRequestValidator.validate(searchPlannedVisitsByPersonalCodeRequest)).thenReturn(errorList);

        SearchPlannedVisitsByPersonalCodeResponse searchPlannedVisitsByPersonalCodeResponse = searchPlannedVisitsByPersonalCodeService.execute(searchPlannedVisitsByPersonalCodeRequest);

        assertTrue(searchPlannedVisitsByPersonalCodeResponse.hasErrors());
        assertTrue(searchPlannedVisitsByPersonalCodeResponse.getErrors().size() == 1);
        searchPlannedVisitsByPersonalCodeResponse.getErrors().forEach(System.out::println);
        assertTrue(searchPlannedVisitsByPersonalCodeResponse.getErrors().contains(error));
    }

    @Test
    public void testEmptyDatabase() {
        SearchPlannedVisitsByPersonalCodeRequest searchPlannedVisitsByPersonalCodeRequest = new SearchPlannedVisitsByPersonalCodeRequest(null);
        List<CoreError> errorList = new ArrayList<>();
        CoreError error = new CoreError("database", "Database is empty");
        errorList.add(error);

        Mockito.when(searchPlannedVisitsByPersonalCodeRequestValidator.validate(searchPlannedVisitsByPersonalCodeRequest)).thenReturn(new ArrayList<>());
        Mockito.when(plannedVisitsRepository.searchPlannedVisitsByPersonalCode(searchPlannedVisitsByPersonalCodeRequest.getPersonalCode())).thenReturn(new ArrayList<>());

        SearchPlannedVisitsByPersonalCodeResponse searchPlannedVisitsByPersonalCodeResponse = searchPlannedVisitsByPersonalCodeService.execute(searchPlannedVisitsByPersonalCodeRequest);

        assertTrue(searchPlannedVisitsByPersonalCodeResponse.hasErrors());
        assertTrue(searchPlannedVisitsByPersonalCodeResponse.getErrors().size() == 1);
        searchPlannedVisitsByPersonalCodeResponse.getErrors().forEach(System.out::println);
        assertTrue(searchPlannedVisitsByPersonalCodeResponse.getErrors().contains(error));
    }

    @Test
    public void testSearchSuccessful() {
        SearchPlannedVisitsByPersonalCodeRequest searchPlannedVisitsByPersonalCodeRequest = new SearchPlannedVisitsByPersonalCodeRequest(null);
        List<PlannedVisit> plannedVisitList = new ArrayList<>();

        PersonalData validPersonalData = new PersonalData("Name", "Surname", "12345678", "01012547896");
        GregorianCalendar gregorianCalendar = new GregorianCalendar(2021, Calendar.MAY, 03, 15, 30);
        Doctor doctor = new Doctor("Doki", "Shpric", "12345678");
        PlannedVisit plannedVisit = new PlannedVisit(gregorianCalendar, validPersonalData, doctor);
        plannedVisitList.add(plannedVisit);

        Mockito.when(searchPlannedVisitsByPersonalCodeRequestValidator.validate(searchPlannedVisitsByPersonalCodeRequest)).thenReturn(new ArrayList<>());
        Mockito.when(plannedVisitsRepository.searchPlannedVisitsByPersonalCode(searchPlannedVisitsByPersonalCodeRequest.getPersonalCode())).thenReturn(plannedVisitList);

        SearchPlannedVisitsByPersonalCodeResponse searchPlannedVisitsByPersonalCodeResponse = searchPlannedVisitsByPersonalCodeService.execute(searchPlannedVisitsByPersonalCodeRequest);

        assertFalse(searchPlannedVisitsByPersonalCodeResponse.hasErrors());
        assertTrue(searchPlannedVisitsByPersonalCodeResponse.getPlannedVisitList().size() == 1);
    }
}*/