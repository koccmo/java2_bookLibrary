package dental_clinic.core.validators.planned_visit;

import dental_clinic.core.requests.plannedVisit.AddPlannedVisitRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class AddPlannedVisitRequestValidator {

    public List <CoreError> validate (AddPlannedVisitRequest addPlannedVisitRequest) {

        List <CoreError> errors = new ArrayList<>();
        errors.addAll(visitTimeValidationErrors(addPlannedVisitRequest.getVisitDataText()));
        errors.addAll(personalCodeValidationErrors(addPlannedVisitRequest.getPersonalCode()));
        errors.addAll(doctorIdValidationErrors(addPlannedVisitRequest));
        return errors;
    }

    private List<CoreError> visitTimeValidationErrors(String visitTime) {
        List <CoreError> errors = new ArrayList<>();
        errors.addAll(visitTimeFormatErrors(visitTime));
        return errors;
    }

    private List <CoreError> visitTimeFormatErrors(String visitTime) {
        List <CoreError> errors = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            dateTimeFormatter.parse(visitTime);
        }
        catch (DateTimeParseException e) {
            errors.add(new CoreError("date", "Not valid input for date"));
        }
        return errors;
    }

    private List<CoreError> personalCodeValidationErrors(String personalCode){
        List <CoreError> errors = new ArrayList<>();
        if (!Pattern.matches("[0-9]{2}[0,1][0-9][0-9][0-9]-?[0-9]{5}", personalCode)) {
            errors.add(new CoreError("Personal data : personal code", "Valid personal format is DDMMYYNNNNN or DDMMYY-NNNNN, where N is digit"));
        }
        return errors;
    }

    private List <CoreError> doctorIdValidationErrors (AddPlannedVisitRequest addPlannedVisitRequest) {
        List <CoreError> errors = new ArrayList<>();
        if (addPlannedVisitRequest.getDoctorsId() == null || addPlannedVisitRequest.getDoctorsId() < 1) {
            errors.add(new CoreError("id", "Not valid input for id"));
        }
        return errors;
    }

}
