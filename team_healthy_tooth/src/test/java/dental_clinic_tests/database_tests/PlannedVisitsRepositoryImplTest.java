package dental_clinic_tests.database_tests;
/*
import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.core.database.planned_visit.PlannedVisitsRepository;
import dental_clinic.core.database.planned_visit.PlannedVisitsRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class PlannedVisitsRepositoryImplTest {

    PlannedVisitsRepository plannedVisitsRepository = new PlannedVisitsRepositoryImpl();
    GregorianCalendar visitTime = new GregorianCalendar(2021, Calendar.MARCH, 25, 15, 30);
    PersonalData personalData = new PersonalData("Name", "Surname", "12345678", "01012087412");
    Doctor doctor = new Doctor("Bob", "Doki", "12345678");
    PlannedVisit plannedVisit = new PlannedVisit(visitTime, personalData, doctor);

    @Before
    public void init(){
        plannedVisitsRepository.addPlannedVisit(plannedVisit);
    }

    @Test
    public void testGetAllPlannedVisits() {
        List<PlannedVisit> visits= plannedVisitsRepository.getPlannedVisits();

        assertTrue(visits.size() == 1);
        assertTrue(visits.get(0).getPersonalData().getName().equals("Name"));
    }

    @Test
    public void testAddPlannedVisit() {
        GregorianCalendar visitTime1 = new GregorianCalendar(2021, Calendar.FEBRUARY, 25);
        PersonalData personalData1 = new PersonalData("Nameeee", "Surname", "12345668", "01012687412");
        PlannedVisit newPlannedVisit = new PlannedVisit(visitTime1, personalData1, doctor);
        plannedVisitsRepository.addPlannedVisit(newPlannedVisit);

        assertTrue(plannedVisitsRepository.getPlannedVisits().size() == 2);
        assertTrue(plannedVisitsRepository.getPlannedVisits().get(1).getPersonalData().getName().equals("Nameeee"));
    }

    @Test
    public void testCancelPlannedVisitNotValidId() {
        plannedVisitsRepository.cancelPlannedVisit(2L);

        assertTrue(plannedVisitsRepository.getPlannedVisits().size() == 1);
    }

    @Test
    public void testCancelPlannedVisit() {
        plannedVisitsRepository.cancelPlannedVisit(1L);
        assertTrue(plannedVisitsRepository.getPlannedVisits().size() == 0);
    }

    @Test
    public void testContainsId() {
        assertTrue(plannedVisitsRepository.containsId(1L));
        assertFalse(plannedVisitsRepository.containsId(2L));
    }

    @Test
    public void containsPlannedVisitInTheSameTimeAndDateTrue() {
        PersonalData personalData1 = new PersonalData("Bob", "Surname", "12345678", "01032087412");
        PlannedVisit plannedVisit1 = new PlannedVisit(visitTime, personalData1, doctor);

        assertTrue(plannedVisitsRepository.containsPlannedVisitInTheSameTimeTheSameDoctor(plannedVisit1));
    }

    @Test
    public void containsPlannedVisitInTheSameTimeFalse() {
        PersonalData personalData1 = new PersonalData("Bob", "Surname", "12345678", "01032087412");
        GregorianCalendar visitTime1 = new GregorianCalendar(2021, Calendar.MARCH, 26, 15, 30);
        PlannedVisit plannedVisit1 = new PlannedVisit(visitTime1, personalData1, doctor );

        assertFalse(plannedVisitsRepository.containsPlannedVisitInTheSameTimeTheSameDoctor(plannedVisit1));
    }

    @Test
    public void testByPersonalCode() {
        assertTrue(plannedVisitsRepository.searchPlannedVisitsByPersonalCode("14018578963").isEmpty());
        assertTrue(plannedVisitsRepository.searchPlannedVisitsByPersonalCode("01012087412").size() == 1);
    }

    @Test
    public void testSearchByDate() {
        assertTrue(plannedVisitsRepository.searchPlannedVisitsByDate(24, 26,03, 04).size() == 1);
        assertTrue(plannedVisitsRepository.searchPlannedVisitsByDate(14, 15,03, 04).size() == 1);
        assertTrue(plannedVisitsRepository.searchPlannedVisitsByDate(01, 15,01, 04).size() == 1);
        assertTrue(plannedVisitsRepository.searchPlannedVisitsByDate(25, 25,03, 03).size() == 1);

        assertTrue(plannedVisitsRepository.searchPlannedVisitsByDate(14, 16, 03, 03).isEmpty());
        assertTrue(plannedVisitsRepository.searchPlannedVisitsByDate(14, 12, 03, 03).isEmpty());
        assertTrue(plannedVisitsRepository.searchPlannedVisitsByDate(0, 0, 0, 0).isEmpty());
    }

}*/