package dental_clinic.core.validators.planned_visit;

import dental_clinic.core.requests.plannedVisit.SearchPlannedVisitsByPersonalCodeRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class SearchPlannedVisitsByPersonalCodeRequestValidator {

    public List<CoreError> validate (SearchPlannedVisitsByPersonalCodeRequest searchPlannedVisitsByPersonalCodeRequest) {

        List <CoreError> errors = new ArrayList<>();

        if (searchPlannedVisitsByPersonalCodeRequest.getPersonalCode() == null || searchPlannedVisitsByPersonalCodeRequest.getPersonalCode().isEmpty()) {
            errors.add(new CoreError("personal code", "Personal code can't be empty"));
        } else {
            errors.addAll(personalCodeFormatErrors(searchPlannedVisitsByPersonalCodeRequest.getPersonalCode()));
        }

        return errors;
    }

    private List<CoreError> personalCodeFormatErrors(String personalCode){
        List <CoreError> errors = new ArrayList<>();
        if (!Pattern.matches("[0-9]{2}[0,1][0-9][0-9][0-9]-?[0-9]{5}", personalCode)) {
            errors.add(new CoreError("Personal data : personal code", "Valid personal format is DDMMYYNNNNN or DDMMYY-NNNNN, where N is digit"));
        }
        return errors;
    }
}
