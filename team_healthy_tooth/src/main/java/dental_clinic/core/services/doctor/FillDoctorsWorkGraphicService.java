package dental_clinic.core.services.doctor;

import dental_clinic.core.requests.doctor.FillDoctorsWorkGraphicRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.doctor.FillDoctorsWorkGraphicResponse;
import dental_clinic.core.validators.doctor.FillDoctorsWorkGraphicRequestValidator;
import dental_clinic.database.in_memory.doctor.DoctorDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class FillDoctorsWorkGraphicService {

    @Autowired
    private FillDoctorsWorkGraphicRequestValidator fillDoctorsWorkGraphicRequestValidator;
    @Autowired
    private DoctorDatabase doctorDatabase;

    public FillDoctorsWorkGraphicResponse execute (FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest) {

        List <CoreError> errorList = fillDoctorsWorkGraphicRequestValidator.validate(fillDoctorsWorkGraphicRequest);

        if (!errorList.isEmpty()) {
            return new FillDoctorsWorkGraphicResponse(errorList);
        }

        if (!doctorDatabase.containsId(fillDoctorsWorkGraphicRequest.getId())) {
            errorList.add(new CoreError("database", "Database doesn't contain id " +
                    fillDoctorsWorkGraphicRequest.getId()));
            return new FillDoctorsWorkGraphicResponse(errorList);
        }

        GregorianCalendar timeFrom = getWorkGraphicTime(fillDoctorsWorkGraphicRequest.getStart());
        GregorianCalendar timeTo = getWorkGraphicTime(fillDoctorsWorkGraphicRequest.getEnd());

        errorList.addAll(timeLogicErrors(timeFrom, timeTo));
        if (!errorList.isEmpty()) {
            return new FillDoctorsWorkGraphicResponse(errorList);
        }

        doctorDatabase.updateWorkGraphicForSpecificDate(fillDoctorsWorkGraphicRequest.getId(), fillDoctorsWorkGraphicRequest.getDay(),
                fillDoctorsWorkGraphicRequest.getStart(), fillDoctorsWorkGraphicRequest.getEnd());
        return new FillDoctorsWorkGraphicResponse(fillDoctorsWorkGraphicRequest.getId(), fillDoctorsWorkGraphicRequest.getDay(),
                fillDoctorsWorkGraphicRequest.getStart(), fillDoctorsWorkGraphicRequest.getEnd());
    }

    private GregorianCalendar getWorkGraphicTime(String time) {
        GregorianCalendar workTime = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        try {
            Date date = simpleDateFormat.parse(time);
            workTime.setTime(date);
        }
        catch (ParseException e) {
            System.out.println("Unexpected error!");
        }
        return workTime;
    }

    private List <CoreError> timeLogicErrors (GregorianCalendar dateStart, GregorianCalendar dateEnd) {
        List <CoreError> errorList = new ArrayList<>();

        if (dateStart.after(dateEnd)) {
            errorList.add(new CoreError("time", "Not valid time"));
        }
        return errorList;
    }

}
