package dental_clinic.core.services.planned_visit;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.requests.plannedVisit.AddPlannedVisitRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.AddPlannedVisitResponse;
import dental_clinic.core.services.patient.AddPatientService;
import dental_clinic.core.validators.planned_visit.AddPlannedVisitRequestValidator;
import dental_clinic.database.in_memory.patient.PatientDatabase;
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
    @Autowired
    private PatientDatabase patientDatabase;
    @Autowired
    private AddPatientService addPatientService;

    public AddPlannedVisitResponse execute (AddPlannedVisitRequest addPlannedVisitRequest) {

        if (!addPlannedVisitRequest.getIsNewPatient()) {
            addPlannedVisitRequest = fillPersonalData(addPlannedVisitRequest);
        } else {
            AddPatientRequest addPatientRequest = new AddPatientRequest(addPlannedVisitRequest.getPersonalData());
            addPatientService.execute(addPatientRequest);
        }

        List<CoreError> errorList = addPlannedVisitRequestValidator.validate(addPlannedVisitRequest);
        if (!errorList.isEmpty()) {
            return new AddPlannedVisitResponse(errorList);
        }

        GregorianCalendar visitDate = getVisitDate(addPlannedVisitRequest.getVisitDataText());
        errorList.addAll(dateInFuture(visitDate));
        if (!errorList.isEmpty()) {
            return new AddPlannedVisitResponse(errorList);
        }

        PlannedVisit plannedVisit = new PlannedVisit(visitDate, addPlannedVisitRequest.getPersonalData());

        if (plannedVisitsInMemoryDatabase.containsPlannedVisitInTheSameTime(plannedVisit)) {
            errorList.add(new CoreError("database", "Not empty time"));
            return new AddPlannedVisitResponse(errorList);
        }

        plannedVisitsInMemoryDatabase.addPlannedVisit(plannedVisit);
        return new AddPlannedVisitResponse(plannedVisit);
    }

    private GregorianCalendar getVisitDate (String visitDateText) {
        GregorianCalendar visitDateDateFormat = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date date = simpleDateFormat.parse(visitDateText);
            visitDateDateFormat.setTime(date);
        }
        catch (ParseException e) {
            System.out.println("Unexpected error!");
        }
        return visitDateDateFormat;
    }

    private List<CoreError> dateInFuture (GregorianCalendar visitDate) {
        List<CoreError> errors = new ArrayList<>();
        GregorianCalendar currentDate = new GregorianCalendar();
        if (visitDate.before(currentDate)) {
            errors.add(new CoreError("date", "Visit date must be in future"));
        }
        return errors;
    }

    private AddPlannedVisitRequest fillPersonalData (AddPlannedVisitRequest addPlannedVisitRequest1) {
        PersonalData personalData = patientDatabase.findPatientsByPersonalCode(addPlannedVisitRequest1.getPersonalData().getPersonalCode()).get(0).getPersonalData();
        return new AddPlannedVisitRequest(false, addPlannedVisitRequest1.getVisitDataText(), personalData);
    }
}
