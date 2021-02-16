package dental_clinic.core.validators.doctor;

import dental_clinic.core.requests.doctor.FillDoctorsWorkGraphicRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

@Component
public class FillDoctorsWorkGraphicRequestValidator {

    public List<CoreError> validate (FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest) {
        List <CoreError> errorList = new ArrayList<>();

        errorList.addAll(isFilledNotCorrect(fillDoctorsWorkGraphicRequest));
        errorList.addAll(idValidationErrors(fillDoctorsWorkGraphicRequest.getId()));
        errorList.addAll(workGraphicTimeFormatErrors(fillDoctorsWorkGraphicRequest.getStart(), fillDoctorsWorkGraphicRequest.getEnd()));

        return errorList;
    }

    private List<CoreError> isFilledNotCorrect(FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest) {
        List <CoreError> errorList = new ArrayList<>();
        if (fillDoctorsWorkGraphicRequest.getDay() == null
                || fillDoctorsWorkGraphicRequest.getDay() < 1 || fillDoctorsWorkGraphicRequest.getDay() > 7) {
            errorList.add(new CoreError("day", "Not valid input for day"));
        }
        if (fillDoctorsWorkGraphicRequest.getStart() == null || fillDoctorsWorkGraphicRequest.getStart().isEmpty()
                || fillDoctorsWorkGraphicRequest.getEnd() == null || fillDoctorsWorkGraphicRequest.getEnd().isEmpty()) {
            errorList.add(new CoreError("time", "Time can't be empty"));
        }
        return errorList;
    }

    private List <CoreError> idValidationErrors(Long id) {
        List <CoreError> errorList = new ArrayList<>();
        if (id == null || id < 1) {
            errorList.add(new CoreError("id", "Not valid input for id"));
        }
        return errorList;
    }

    private List <CoreError> workGraphicTimeFormatErrors(String startTime, String endTime) {
        List <CoreError> errors = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            dateTimeFormatter.parse(startTime);
            dateTimeFormatter.parse(endTime);
        }
        catch (DateTimeParseException e) {
            errors.add(new CoreError("time", "Not valid input for time"));
        }
        return errors;
    }
}
