package dental_clinic.core.validators.planned_visit;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.plannedVisit.AddPlannedVisitRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class AddPlannedVisitRequestValidator {

    public List<CoreError> validate (AddPlannedVisitRequest addPlannedVisitRequest) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(visitTimeErrors(addPlannedVisitRequest.getVisitData()));
        errors.addAll(personalDataValidationErrors(addPlannedVisitRequest.getPersonalData()));
        return errors;
    }

    private List<CoreError> visitTimeErrors (String visitTime) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(visitTimeFormatErrors(visitTime));
        return errors;
    }

    private List<CoreError> visitTimeFormatErrors(String visitTime) {
        List<CoreError> errors = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            simpleDateFormat.parse(visitTime);
        }
        catch (ParseException e) {
            errors.add(new CoreError("date", "Not valid input for date"));
        }
        return errors;
    }

    private List<CoreError> personalDataValidationErrors (PersonalData personalData) {
        List <CoreError> errors = new ArrayList<>();
        errors.addAll(nameValidationErrors(personalData.getName()));
        errors.addAll(surnameValidationErrors(personalData.getSurname()));
        errors.addAll(phoneValidationErrors(personalData.getPhone()));
        errors.addAll(personalCodeValidationErrors(personalData.getPersonalCode()));
        return errors;
    }

    private List<CoreError> nameValidationErrors(String name){
        List <CoreError> errors = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            errors.add(new CoreError("Personal data : name", "Name can't be empty"));
        }else{
            if (!name.matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")){
                errors.add(new CoreError("Personal data : name", "Name can contain only letters"));
            }
        }
        return errors;
    }

    private List<CoreError> surnameValidationErrors(String surname){
        List <CoreError> errors = new ArrayList<>();
        if (surname == null || surname.isEmpty()){
            errors.add(new CoreError("Personal data : surname", "Surname can't be empty"));
        } else{
            if (!surname.matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")) {
                errors.add(new CoreError("Personal data : surname", "Surname can contain only letters"));
            }
        }
        return errors;
    }

    private List<CoreError> phoneValidationErrors(String phone){
        List <CoreError> errors = new ArrayList<>();
        if (!phone.matches("\\d{8}|\\d{11}|\\d{12}")) {
            errors.add(new CoreError("Personal data : phone", "Phone must contain 8 or 11 or 12 digits"));
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

}
