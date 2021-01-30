package dental_clinic.core.services.planned_visit;

import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.core.requests.plannedVisit.SearchPlannedVisitsByDateRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.SearchPlannedVisitsByDateResponse;
import dental_clinic.core.validators.planned_visit.SearchPlannedVisitsByDateRequestValidator;
import dental_clinic.core.database.planned_visit.PlannedVisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchPlannedVisitsByDateService {

    @Autowired
    private SearchPlannedVisitsByDateRequestValidator searchPlannedVisitsByDateRequestValidator;
    @Autowired
    private PlannedVisitsRepository plannedVisitsRepository;

    public SearchPlannedVisitsByDateResponse execute (SearchPlannedVisitsByDateRequest searchPlannedVisitsByDateRequest) {
        List<CoreError> errorList = searchPlannedVisitsByDateRequestValidator.validate(searchPlannedVisitsByDateRequest);

        if (!errorList.isEmpty()) {
            return new SearchPlannedVisitsByDateResponse(errorList, new ArrayList<>());
        }

        List <PlannedVisit> plannedVisitList = plannedVisitsRepository.searchPlannedVisitsByDate(searchPlannedVisitsByDateRequest.getDayFrom(),
                searchPlannedVisitsByDateRequest.getDayTo(), searchPlannedVisitsByDateRequest.getMonthFrom(), searchPlannedVisitsByDateRequest.getMonthTo());

        if (plannedVisitList.isEmpty()) {
            errorList.add(new CoreError("database", "Database is empty"));
            return new SearchPlannedVisitsByDateResponse(errorList, new ArrayList<>());
        }

        return new SearchPlannedVisitsByDateResponse(sortedInHronologicalOrder(plannedVisitList));
    }

    private List <PlannedVisit> sortedInHronologicalOrder(List <PlannedVisit> listOfVisits) {
        return listOfVisits.stream()
                .sorted(Comparator.comparing(plannedVisit -> plannedVisit.getVisitTime()))
                .collect(Collectors.toList());
    }
}
