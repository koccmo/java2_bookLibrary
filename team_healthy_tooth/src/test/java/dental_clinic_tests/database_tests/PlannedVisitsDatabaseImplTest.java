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

public class PlannedVisitsDatabaseImplTest {

    PlannedVisitsRepository plannedVisitsDatabase = new PlannedVisitsRepositoryImpl();
    GregorianCalendar visitTime = new GregorianCalendar(2021, Calendar.MARCH, 25, 15, 30);
    PersonalData personalData = new PersonalData("Name", "Surname", "12345678", "01012087412");
    Doctor doctor = new Doctor("Bob", "Doki", "12345678");
    PlannedVisit plannedVisit = new PlannedVisit(visitTime, personalData, doctor);

    @Before
    public void init(){
        plannedVisitsDatabase.addPlannedVisit(plannedVisit);
    }

    @Test
    public void testGetAllPlannedVisits() {
        List<PlannedVisit> visits= plannedVisitsDatabase.getPlannedVisits();

        assertTrue(visits.size() == 1);
        assertTrue(visits.get(0).getPersonalData().getName().equals("Name"));
    }

    @Test
    public void testAddPlannedVisit() {
        GregorianCalendar visitTime1 = new GregorianCalendar(2021, Calendar.FEBRUARY, 25);
        PersonalData personalData1 = new PersonalData("Nameeee", "Surname", "12345668", "01012687412");
        PlannedVisit newPlannedVisit = new PlannedVisit(visitTime1, personalData1, doctor);
        plannedVisitsDatabase.addPlannedVisit(newPlannedVisit);

        assertTrue(plannedVisitsDatabase.getPlannedVisits().size() == 2);
        assertTrue(plannedVisitsDatabase.getPlannedVisits().get(1).getPersonalData().getName().equals("Nameeee"));
    }

    @Test
    public void testCancelPlannedVisitNotValidId() {
        plannedVisitsDatabase.cancelPlannedVisit(2L);

        assertTrue(plannedVisitsDatabase.getPlannedVisits().size() == 1);
    }

    @Test
    public void testCancelPlannedVisit() {
        plannedVisitsDatabase.cancelPlannedVisit(1L);
        assertTrue(plannedVisitsDatabase.getPlannedVisits().size() == 0);
    }

    @Test
    public void testContainsId() {
        assertTrue(plannedVisitsDatabase.containsId(1L));
        assertFalse(plannedVisitsDatabase.containsId(2L));
    }

    @Test
    public void containsPlannedVisitInTheSameTimeAndDateTrue() {
        PersonalData personalData1 = new PersonalData("Bob", "Surname", "12345678", "01032087412");
        PlannedVisit plannedVisit1 = new PlannedVisit(visitTime, personalData1, doctor);

        assertTrue(plannedVisitsDatabase.containsPlannedVisitInTheSameTimeTheSameDoctor(plannedVisit1));
    }

    @Test
    public void containsPlannedVisitInTheSameTimeFalse() {
        PersonalData personalData1 = new PersonalData("Bob", "Surname", "12345678", "01032087412");
        GregorianCalendar visitTime1 = new GregorianCalendar(2021, Calendar.MARCH, 26, 15, 30);
        PlannedVisit plannedVisit1 = new PlannedVisit(visitTime1, personalData1, doctor );

        assertFalse(plannedVisitsDatabase.containsPlannedVisitInTheSameTimeTheSameDoctor(plannedVisit1));
    }

    @Test
    public void testByPersonalCode() {
        assertTrue(plannedVisitsDatabase.searchPlannedVisitsByPersonalCode("14018578963").isEmpty());
        assertTrue(plannedVisitsDatabase.searchPlannedVisitsByPersonalCode("01012087412").size() == 1);
    }

    @Test
    public void testSearchByDate() {
        assertTrue(plannedVisitsDatabase.searchPlannedVisitsByDate(24, 26,03, 04).size() == 1);
        assertTrue(plannedVisitsDatabase.searchPlannedVisitsByDate(14, 15,03, 04).size() == 1);
        assertTrue(plannedVisitsDatabase.searchPlannedVisitsByDate(01, 15,01, 04).size() == 1);
        assertTrue(plannedVisitsDatabase.searchPlannedVisitsByDate(25, 25,03, 03).size() == 1);

        assertTrue(plannedVisitsDatabase.searchPlannedVisitsByDate(14, 16, 03, 03).isEmpty());
        assertTrue(plannedVisitsDatabase.searchPlannedVisitsByDate(14, 12, 03, 03).isEmpty());
        assertTrue(plannedVisitsDatabase.searchPlannedVisitsByDate(0, 0, 0, 0).isEmpty());
    }

}*/