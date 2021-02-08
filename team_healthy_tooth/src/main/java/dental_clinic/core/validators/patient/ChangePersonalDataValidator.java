package dental_clinic.core.validators.patient;

import dental_clinic.core.requests.patient.ChangePersonalDataRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangePersonalDataValidator {

    public List<CoreError> validate (ChangePersonalDataRequest request) {
        List<CoreError> errors = new ArrayList<>();

        String updatedSurnameToCheck = request.getUpdatedSurname();
        String updaterPhoneNumberToCheck = request.getUpdatedPhoneNumber();

        errors.addAll(idValidationErrors(request));
        errors.addAll(isValidUpdatedSurname(updatedSurnameToCheck));
        errors.addAll(isValidUpdatedPhoneNumber(updaterPhoneNumberToCheck));

        return errors;
    }

    private List<CoreError> idValidationErrors (ChangePersonalDataRequest changePersonalDataRequest) {
        List<CoreError> errors = new ArrayList<>();
        if (changePersonalDataRequest.getPatientIdNumber() == null || changePersonalDataRequest.getPatientIdNumber() < 1) {
            errors.add(new CoreError("id", "Not valid id number"));
        }
        return errors;
    }

    private List<CoreError> isValidUpdatedSurname(String nameToCheck) {
        List<CoreError> errors = new ArrayList<>();
        if (nameToCheck != null && !nameToCheck.isEmpty()) {
            if (!nameToCheck.matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")) {
                errors.add(new CoreError("Personal data : surname", "Invalid input! Can only contain letters!"));
            }
        }
        return errors;
    }

    private List<CoreError> isValidUpdatedPhoneNumber(String phoneNumberToCheck) {
        List<CoreError> errors = new ArrayList<>();
        if (phoneNumberToCheck != null && !phoneNumberToCheck.isEmpty()) {
            if (!phoneNumberToCheck.matches("\\d{8}|\\d{11}|\\d{12}")) {
                errors.add(new CoreError(
                        "Personal data : phone",
                        "Invalid input! Can only contain 8 or 11 or 12 digits!"));
            }
        }
        return errors;
    }
}