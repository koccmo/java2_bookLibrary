package dental_clinic.database.in_memory.planned_visit;

import dental_clinic.core.domain.PlannedVisit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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
}
