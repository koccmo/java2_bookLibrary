package dental_clinic.database.in_memory.planned_visit;

import dental_clinic.core.domain.PlannedVisit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlannedVisitsInMemoryDatabaseImpl implements PlannedVisitsInMemoryDatabase {

    private List<PlannedVisit> plannedVisitsList = new ArrayList<>();
    private Long id = 1L;

    @Override
    public List<PlannedVisit> getPlannedVisits() {
        return plannedVisitsList;
    }

    @Override
    public void addPlannedVisit(PlannedVisit plannedVisit) {
        plannedVisit.setId(id);
        id++;
        plannedVisitsList.add(plannedVisit);
    }

    @Override
    public void cancelPlannedVisit(Long id) {
        plannedVisitsList.removeIf(plannedVisit -> plannedVisit.getId().equals(id));
    }

    @Override
    public void changePlannedVisitTime(Long id, GregorianCalendar visitTime) {
        for (PlannedVisit eVisit: plannedVisitsList) {
            if (eVisit.getId().equals(id)) {
                eVisit.setVisitTime(visitTime);
            }
        }
    }

    @Override
    public boolean containsId(Long id) {
        return plannedVisitsList.stream()
                .anyMatch(plannedVisit -> plannedVisit.getId().equals(id));
    }

    @Override
    public boolean containsPlannedVisitInTheSameTime(PlannedVisit plannedVisit) {
        return plannedVisitsList.stream()
                .anyMatch(plannedVisit1 -> plannedVisit1.getVisitTime().equals(plannedVisit.getVisitTime()));
    }

    @Override
    public List<PlannedVisit> searchByPersonalCode(String personalCode) {
        return plannedVisitsList.stream()
                .filter(plannedVisit -> plannedVisit.getPersonalData().getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlannedVisit> searchByDate(int dayFrom, int dayTo, int monthFrom, int monthTo) {
        GregorianCalendar dateFrom = new GregorianCalendar(2021, monthFrom-1, dayFrom, 00, 00);
        GregorianCalendar dateTo = new GregorianCalendar(2021, monthTo-1, dayTo, 23, 59);
        return plannedVisitsList.stream()
                .filter(plannedVisit -> (plannedVisit.getVisitTime().after(dateFrom)
                        && plannedVisit.getVisitTime().before(dateTo)) ||
                        (plannedVisit.getVisitTime().equals(dateFrom) ||
                        plannedVisit.getVisitTime().equals(dateTo)))
                .collect(Collectors.toList());
    }
}
