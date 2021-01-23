package dental_clinic.database.in_memory.visit;

import dental_clinic.core.domain.Visit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VisitDatabaseImplInMemory implements VisitDatabase{

    private List<Visit> visitList = new ArrayList<>();
    private Long id = 1L;

    @Override
    public void addVisit(Visit visit) {
        visit.setId(id);
        id++;
        visitList.add(visit);
    }
}
