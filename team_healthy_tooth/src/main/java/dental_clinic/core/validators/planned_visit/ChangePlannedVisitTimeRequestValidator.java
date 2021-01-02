package dental_clinic.core.validators.planned_visit;

import dental_clinic.core.requests.plannedVisit.ChangePlannedVisitTimeRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ChangePlannedVisitTimeRequestValidator {

    public List<CoreError> validate (ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(idErrors(changePlannedVisitTimeRequest.getId()));
        errors.addAll(visitTimeFormatErrors(changePlannedVisitTimeRequest.getVisitTime()));
        return errors;
    }

    private List<CoreError> idErrors (Long id) {
        List<CoreError> errors = new ArrayList<>();
        if (id == null || id < 1L) {
            errors.add(new CoreError("id", "Not valid input for id"));
        }
        return errors;
    }

    private List<CoreError> visitTimeFormatErrors(String visitTime) {
        List<CoreError> errors = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date date = simpleDateFormat.parse(visitTime);
            //GregorianCalendar gregorianCalendar = new GregorianCalendar();
            //gregorianCalendar.setTime(date);
        }
        catch (ParseException e) {
            errors.add(new CoreError("date", "Not valid input for date"));
        }
        return errors;
    }
}
