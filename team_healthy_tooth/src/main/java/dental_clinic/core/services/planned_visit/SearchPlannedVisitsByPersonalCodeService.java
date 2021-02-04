package dental_clinic.core.services.planned_visit;

import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.core.requests.plannedVisit.SearchPlannedVisitsByPersonalCodeRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.SearchPlannedVisitsByPersonalCodeResponse;
import dental_clinic.core.validators.planned_visit.SearchPlannedVisitsByPersonalCodeRequestValidator;
import dental_clinic.core.database.planned_visit.PlannedVisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchPlannedVisitsByPersonalCodeService {

    @Autowired
    private SearchPlannedVisitsByPersonalCodeRequestValidator searchPlannedVisitsByPersonalCodeRequestValidator;
    @Autowired
    private PlannedVisitsRepository plannedVisitsRepository;

    public SearchPlannedVisitsByPersonalCodeResponse execute (SearchPlannedVisitsByPersonalCodeRequest searchPlannedVisitsByPersonalCodeRequest) {
        List<CoreError> errorList = searchPlannedVisitsByPersonalCodeRequestValidator.validate(searchPlannedVisitsByPersonalCodeRequest);

        if (!errorList.isEmpty()) {
            return new SearchPlannedVisitsByPersonalCodeResponse(errorList, new ArrayList<>());
        }

        List <PlannedVisit> plannedVisitList
                = plannedVisitsRepository.searchPlannedVisitsByPersonalCode(searchPlannedVisitsByPersonalCodeRequest.getPersonalCode());
        if (plannedVisitList.isEmpty()) {
            errorList.add(new CoreError("database", "Database is empty"));
            return new SearchPlannedVisitsByPersonalCodeResponse(errorList, new ArrayList<>());
        }

        return new SearchPlannedVisitsByPersonalCodeResponse(sortedInHronologicalOrder(plannedVisitList));
    }

    private List <PlannedVisit> sortedInHronologicalOrder(List <PlannedVisit> listOfVisits) {
        return listOfVisits.stream()
                .sorted(Comparator.comparing(plannedVisit -> plannedVisit.getVisitTime()))
                .collect(Collectors.toList());
    }


}
