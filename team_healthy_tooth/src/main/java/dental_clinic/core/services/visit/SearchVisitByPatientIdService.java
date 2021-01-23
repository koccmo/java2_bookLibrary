package dental_clinic.core.services.visit;

import dental_clinic.core.domain.Visit;
import dental_clinic.core.requests.visit.SearchVisitByPatientIdRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.visit.SearchVisitByPatientIdResponse;
import dental_clinic.core.validators.visit.SearchVisitByPatientIdValidator;
import dental_clinic.database.in_memory.visit.VisitDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchVisitByPatientIdService {

    @Autowired
    private SearchVisitByPatientIdValidator searchVisitByPatientIdValidator;
    @Autowired
    private VisitDatabase visitDatabase;

    public SearchVisitByPatientIdResponse execute (SearchVisitByPatientIdRequest searchVisitByPatientIdRequest) {
        List<CoreError> errorList = searchVisitByPatientIdValidator.validate(searchVisitByPatientIdRequest);

        if (!errorList.isEmpty()) {
            return new SearchVisitByPatientIdResponse(errorList, new ArrayList<>());
        }

        List<Visit> visitList = visitDatabase.searchVisitByPatientId(searchVisitByPatientIdRequest.getId());
        if (visitList.isEmpty()) {
            errorList.add(new CoreError("database", "Database doesn't contain visit for patient with id " +
                    searchVisitByPatientIdRequest.getId()));
            return new SearchVisitByPatientIdResponse(errorList, new ArrayList<>());
        }

        return new SearchVisitByPatientIdResponse(visitList);
    }
}
