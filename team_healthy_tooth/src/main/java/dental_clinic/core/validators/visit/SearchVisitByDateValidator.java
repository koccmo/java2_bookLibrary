package dental_clinic.core.validators.visit;

import dental_clinic.core.requests.visit.SearchVisitByDateRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchVisitByDateValidator {

    public List<CoreError> validate (SearchVisitByDateRequest searchVisitByDateRequest) {
        List <CoreError> errors = new ArrayList<>();
        if (notValidInputForDay(searchVisitByDateRequest.getDayFrom(), searchVisitByDateRequest.getDayTo())
                || notValidInputForMonth(searchVisitByDateRequest.getMonthFrom(), searchVisitByDateRequest.getMonthTo())) {
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
