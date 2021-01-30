package dental_clinic.core.services.planned_visit;

import dental_clinic.core.requests.plannedVisit.ChangePlannedVisitTimeRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.ChangePlannedVisitTimeResponse;
import dental_clinic.core.validators.planned_visit.ChangePlannedVisitTimeRequestValidator;
import dental_clinic.core.database.planned_visit.PlannedVisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class ChangePlannedVisitTimeService {

    @Autowired
    private PlannedVisitsRepository plannedVisitsRepository;
    @Autowired
    private ChangePlannedVisitTimeRequestValidator changePlannedVisitTimeRequestValidator;

    public ChangePlannedVisitTimeResponse execute (ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest) {
        List<CoreError> errorList = changePlannedVisitTimeRequestValidator.validate(changePlannedVisitTimeRequest);

        if (!errorList.isEmpty()) {
            return new ChangePlannedVisitTimeResponse(errorList);
        }

        if (!plannedVisitsRepository.containsId(changePlannedVisitTimeRequest.getId())) {
            errorList.add(new CoreError("database", "Database doesn't contain id "
                    + changePlannedVisitTimeRequest.getId()));
            return new ChangePlannedVisitTimeResponse(errorList);
        }

        GregorianCalendar visitDate = getVisitDate(changePlannedVisitTimeRequest.getVisitTime());
        errorList.addAll(dateNotInFuture(visitDate));
        if (!errorList.isEmpty()) {
            return new ChangePlannedVisitTimeResponse(errorList);
        }

        plannedVisitsRepository.changePlannedVisitTime(changePlannedVisitTimeRequest.getId(), visitDate);
        return new ChangePlannedVisitTimeResponse(changePlannedVisitTimeRequest.getId(), visitDate);
    }

    private GregorianCalendar getVisitDate (String visitDate) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date date = simpleDateFormat.parse(visitDate);
            gregorianCalendar.setTime(date);
        }
        catch (ParseException e) {

        }
        return gregorianCalendar;
    }

    private List<CoreError> dateNotInFuture(GregorianCalendar visitDate) {
        List<CoreError> errors = new ArrayList<>();
        GregorianCalendar currentDate = new GregorianCalendar();
        if (visitDate.before(currentDate)) {
            errors.add(new CoreError("date", "Visit date must be in future"));
        }
        return errors;
    }
}
