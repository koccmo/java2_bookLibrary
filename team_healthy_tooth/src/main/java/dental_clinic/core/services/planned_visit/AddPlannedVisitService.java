package dental_clinic.core.services.planned_visit;

import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.core.requests.plannedVisit.AddPlannedVisitRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.AddPlannedVisitResponse;
import dental_clinic.core.validators.planned_visit.AddPlannedVisitRequestValidator;
import dental_clinic.database.in_memory.planned_visit.PlannedVisitsInMemoryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class AddPlannedVisitService {

    @Autowired
    private AddPlannedVisitRequestValidator addPlannedVisitRequestValidator;
    @Autowired
    private PlannedVisitsInMemoryDatabase plannedVisitsInMemoryDatabase;

    public AddPlannedVisitResponse execute(AddPlannedVisitRequest addPlannedVisitRequest) {
        List<CoreError> errorList = addPlannedVisitRequestValidator.validate(addPlannedVisitRequest);
        if (!errorList.isEmpty()) {
            return new AddPlannedVisitResponse(errorList);
        }

        GregorianCalendar visitDate = getVisitDate(addPlannedVisitRequest.getVisitData());
        errorList.addAll(dateInFuture(visitDate));
        if (!errorList.isEmpty()) {
            return new AddPlannedVisitResponse(errorList);
        }

        PlannedVisit plannedVisit = new PlannedVisit(visitDate, addPlannedVisitRequest.getPersonalData());
        plannedVisitsInMemoryDatabase.addPlannedVisit(plannedVisit);
        return new AddPlannedVisitResponse(plannedVisit);
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

    private List<CoreError> dateInFuture (GregorianCalendar visitDate) {
        List<CoreError> errors = new ArrayList<>();
        GregorianCalendar currentDate = new GregorianCalendar();
        if (visitDate.before(currentDate)) {
            errors.add(new CoreError("date", "Visit date must be in future"));
        }
        return errors;
    }
}
