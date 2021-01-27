package dental_clinic.core.database.planned_visit;

import dental_clinic.core.domain.PlannedVisit;

import java.util.GregorianCalendar;
import java.util.List;

public interface PlannedVisitsInMemoryDatabase {

    List <PlannedVisit> getPlannedVisits();

    void addPlannedVisit(PlannedVisit plannedVisit);

    void cancelPlannedVisit(Long id);

    void changePlannedVisitTime(Long id, GregorianCalendar visitTime);

    boolean containsId(Long id);

    boolean containsPlannedVisitInTheSameTimeTheSameDoctor(PlannedVisit plannedVisit);

    List <PlannedVisit> searchPlannedVisitsByPersonalCode(String personalCode);

    List<PlannedVisit> searchPlannedVisitsByDate(int dayFrom, int dayTo, int monthFrom, int monthTo);
}
