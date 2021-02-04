package dental_clinic_tests.core.services_tests.planned_visit;
/*
import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.core.requests.plannedVisit.GetPlannedVisitsRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.GetPlannedVisitsResponse;
import dental_clinic.core.services.planned_visit.GetPlannedVisitsService;
import dental_clinic.core.validators.planned_visit.GetPlannedVisitsRequestValidator;
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
public class GetPlannedVisitsServiceTest {

    @Mock
    private GetPlannedVisitsRequestValidator getPlannedVisitsRequestValidator;
    @Mock
    private PlannedVisitsRepository plannedVisitsRepository;
    @InjectMocks
    private GetPlannedVisitsService getPlannedVisitsService;

    @Test
    public void testEmptyDatabase() {
        CoreError error = new CoreError("database", "Database is empty");
        GetPlannedVisitsRequest getPlannedVisitsRequest = new GetPlannedVisitsRequest();

        Mockito.when(getPlannedVisitsRequestValidator.validate(getPlannedVisitsRequest)).thenReturn(new ArrayList<>());
        Mockito.when(plannedVisitsRepository.getPlannedVisits()).thenReturn(new ArrayList<>());

        GetPlannedVisitsResponse getPlannedVisitsResponse = getPlannedVisitsService.execute(getPlannedVisitsRequest);

        assertTrue(getPlannedVisitsResponse.hasErrors());
        assertTrue(getPlannedVisitsResponse.getErrors().size() == 1);
        assertTrue(getPlannedVisitsResponse.getErrors().contains(error));
    }

    @Test
    public void testGetSuccessfully() {
        GetPlannedVisitsRequest getPlannedVisitsRequest = new GetPlannedVisitsRequest();
        List<PlannedVisit> plannedVisitList = new ArrayList<>();
        GregorianCalendar visitTime = new GregorianCalendar(2021, Calendar.MARCH, 8, 15, 30);
        PersonalData personalData = new PersonalData("Name", "Surname", "12345678", "01011478963");
        Doctor doctor = new Doctor("Bobik", "Shpric", "12345678");
        PlannedVisit plannedVisit = new PlannedVisit(visitTime, personalData, doctor);
        plannedVisitList.add(plannedVisit);
        Mockito.when(getPlannedVisitsRequestValidator.validate(getPlannedVisitsRequest)).thenReturn(new ArrayList<>());
        Mockito.when(plannedVisitsRepository.getPlannedVisits()).thenReturn(plannedVisitList);

        GetPlannedVisitsResponse getPlannedVisitsResponse = getPlannedVisitsService.execute(getPlannedVisitsRequest);

        assertFalse(getPlannedVisitsResponse.hasErrors());
        assertTrue(getPlannedVisitsResponse.getPlannedVisits().size() == 1);
    }

}*/