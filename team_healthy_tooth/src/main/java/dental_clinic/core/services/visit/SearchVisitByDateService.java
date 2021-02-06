package dental_clinic.core.services.visit;

import dental_clinic.core.domain.Visit;
import dental_clinic.core.requests.visit.SearchVisitByDateRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.visit.SearchVisitByDateResponse;
import dental_clinic.core.validators.visit.SearchVisitByDateValidator;
import dental_clinic.core.database.visit.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchVisitByDateService {

    @Autowired
    private SearchVisitByDateValidator searchVisitByDateValidator;
    @Autowired
    private VisitRepository visitRepository;

    public SearchVisitByDateResponse execute (SearchVisitByDateRequest searchVisitByDateRequest) {
        List<CoreError> errorList = searchVisitByDateValidator.validate(searchVisitByDateRequest);

        if (!errorList.isEmpty()) {
            return new SearchVisitByDateResponse(errorList, new ArrayList<>());
        }

        List <Visit> visitList = visitRepository.searchVisitByDate(searchVisitByDateRequest.getDayFrom(),
                searchVisitByDateRequest.getDayTo(), searchVisitByDateRequest.getMonthFrom(), searchVisitByDateRequest.getMonthTo());

        if (visitList.isEmpty()) {
            errorList.add(new CoreError("database", "Database is empty"));
            return new SearchVisitByDateResponse(errorList, new ArrayList<>());
        }

        return new SearchVisitByDateResponse(sortedInHronologicalOrder(visitList));
    }

    private List <Visit> sortedInHronologicalOrder(List <Visit> listOfVisits) {
        return listOfVisits.stream()
                .sorted(Comparator.comparing(visit -> visit.getDate()))
                .collect(Collectors.toList());
    }

}
