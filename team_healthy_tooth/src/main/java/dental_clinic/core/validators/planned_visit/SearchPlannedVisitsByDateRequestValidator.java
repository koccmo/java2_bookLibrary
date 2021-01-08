package dental_clinic.core.validators.planned_visit;

import dental_clinic.core.requests.plannedVisit.SearchPlannedVisitsByDateRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchPlannedVisitsByDateRequestValidator {

    public List<CoreError> validate (SearchPlannedVisitsByDateRequest searchPlannedVisitsByDateRequest) {
        List <CoreError> errors = new ArrayList<>();
        if (notValidInputForDay(searchPlannedVisitsByDateRequest.getDayFrom(), searchPlannedVisitsByDateRequest.getDayTo())
        || notValidInputForMonth(searchPlannedVisitsByDateRequest.getMonthFrom(), searchPlannedVisitsByDateRequest.getMonthTo())) {
            errors.add(new CoreError("date", "Not valid input for date for search"));
        }
        return errors;
    }

    private boolean notValidInputForMonth (int input1, int input2) {
        return input1 < 1 || input1 >12 || input2 < 1 || input2 >12;
    }

    private boolean notValidInputForDay (int input1, int input2) {
        return input1 < 1 || input1 > 31 || input2 < 1 || input2 > 31;
    }
}
