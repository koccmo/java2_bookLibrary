package dental_clinic_tests.database_tests;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.database.in_memory.planned_visit.PlannedVisitsInMemoryDatabase;
import dental_clinic.database.in_memory.planned_visit.PlannedVisitsInMemoryDatabaseImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class PlannedVisitsInMemoryDatabaseImplTest {

    PlannedVisitsInMemoryDatabase plannedVisitsInMemoryDatabase = new PlannedVisitsInMemoryDatabaseImpl();
    GregorianCalendar visitTime = new GregorianCalendar(2021, Calendar.MARCH, 25, 15, 30);
    PersonalData personalData = new PersonalData("Name", "Surname", "12345678", "01012087412");
    PlannedVisit plannedVisit = new PlannedVisit(visitTime, personalData);

    @Before
    public void init(){
        plannedVisitsInMemoryDatabase.addPlannedVisit(plannedVisit);
    }

    @Test
    public void testGetAllPlannedVisits() {
        List<PlannedVisit> visits= plannedVisitsInMemoryDatabase.getPlannedVisits();

        assertTrue(visits.size() == 1);
        assertTrue(visits.get(0).getPersonalData().getName().equals("Name"));
    }

    @Test
    public void testAddPlannedVisit() {
        GregorianCalendar visitTime1 = new GregorianCalendar(2021, Calendar.FEBRUARY, 25);
        PersonalData personalData1 = new PersonalData("Nameeee", "Surname", "12345668", "01012687412");
        PlannedVisit newPlannedVisit = new PlannedVisit(visitTime1, personalData1);
        plannedVisitsInMemoryDatabase.addPlannedVisit(newPlannedVisit);

        assertTrue(plannedVisitsInMemoryDatabase.getPlannedVisits().size() == 2);
        assertTrue(plannedVisitsInMemoryDatabase.getPlannedVisits().get(1).getPersonalData().getName().equals("Nameeee"));
    }

    @Test
    public void testCancelPlannedVisitNotValidId() {
        plannedVisitsInMemoryDatabase.cancelPlannedVisit(2L);

        assertTrue(plannedVisitsInMemoryDatabase.getPlannedVisits().size() == 1);
    }

    @Test
    public void testCancelPlannedVisit() {
        plannedVisitsInMemoryDatabase.cancelPlannedVisit(1L);
        assertTrue(plannedVisitsInMemoryDatabase.getPlannedVisits().size() == 0);
    }

    @Test
    public void testContainsId() {
        assertTrue(plannedVisitsInMemoryDatabase.containsId(1L));
        assertFalse(plannedVisitsInMemoryDatabase.containsId(2L));
    }

    @Test
    public void containsPlannedVisitInTheSameTimeAndDateTrue() {
        PersonalData personalData1 = new PersonalData("Bob", "Surname", "12345678", "01032087412");
        PlannedVisit plannedVisit1 = new PlannedVisit(visitTime, personalData1);

        assertTrue(plannedVisitsInMemoryDatabase.containsPlannedVisitInTheSameTime(plannedVisit1));
    }

    @Test
    public void containsPlannedVisitInTheSameTimeFalse() {
        PersonalData personalData1 = new PersonalData("Bob", "Surname", "12345678", "01032087412");
        GregorianCalendar visitTime1 = new GregorianCalendar(2021, Calendar.MARCH, 26, 15, 30);
        PlannedVisit plannedVisit1 = new PlannedVisit(visitTime1, personalData1);

        assertFalse(plannedVisitsInMemoryDatabase.containsPlannedVisitInTheSameTime(plannedVisit1));
    }

    @Test
    public void testByPersonalCode() {
        assertTrue(plannedVisitsInMemoryDatabase.searchPlannedVisitsByPersonalCode("14018578963").isEmpty());
        assertTrue(plannedVisitsInMemoryDatabase.searchPlannedVisitsByPersonalCode("01012087412").size() == 1);
    }

    @Test
    public void testSearchByDate() {
        assertTrue(plannedVisitsInMemoryDatabase.searchPlannedVisitsByDate(24, 26,03, 04).size() == 1);
        assertTrue(plannedVisitsInMemoryDatabase.searchPlannedVisitsByDate(14, 15,03, 04).size() == 1);
        assertTrue(plannedVisitsInMemoryDatabase.searchPlannedVisitsByDate(01, 15,01, 04).size() == 1);
        assertTrue(plannedVisitsInMemoryDatabase.searchPlannedVisitsByDate(25, 25,03, 03).size() == 1);

        assertTrue(plannedVisitsInMemoryDatabase.searchPlannedVisitsByDate(14, 16, 03, 03).isEmpty());
        assertTrue(plannedVisitsInMemoryDatabase.searchPlannedVisitsByDate(14, 12, 03, 03).isEmpty());
        assertTrue(plannedVisitsInMemoryDatabase.searchPlannedVisitsByDate(0, 0, 0, 0).isEmpty());
    }

}