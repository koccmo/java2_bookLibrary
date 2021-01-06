package dental_clinic.core.validators.visit;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.requests.visit.AddVisitRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddVisitValidator {

    public List<CoreError> validate (AddVisitRequest addVisitRequest){

        List <CoreError> coreErrors = new ArrayList<>();

        coreErrors.addAll(idValidationErrors(addVisitRequest.getPatientsId()));

        coreErrors.addAll(toothNumberValidationErrors(addVisitRequest.getToothNumber()));

        coreErrors.addAll(doctorValidationErrors(addVisitRequest.getDoctor()));

        return coreErrors;
    }

    private List<CoreError> idValidationErrors(Long id) {
        List <CoreError> coreErrors = new ArrayList<>();
        if ((id == null) || (id < 1)){
            coreErrors.add(new CoreError("id", "Not valid input of id"));
        }
        return coreErrors;
    }

    private List<CoreError> toothNumberValidationErrors(int toothNumber) {
        List <CoreError> coreErrors = new ArrayList<>();
        if (!isValidToothNumber(toothNumber)){
            coreErrors.add(new CoreError("tooth number", "Not valid input for tooth number"));
        }
        return coreErrors;
    }

    private boolean isValidToothNumber(int number){
        return (number >=11 && number <= 18) ||
                (number >=21 && number <= 28) ||
                (number >= 31 && number <=38) ||
                (number >= 41 && number <= 48) ||
                (number >= 51 && number <= 55) ||
                (number >= 61 && number <= 65) ||
                (number >= 71 && number <= 75) ||
                (number >= 81 && number <= 85);
    }

    private List<CoreError> doctorValidationErrors (Doctor doctor) {
        List<CoreError> coreErrors = new ArrayList<>();
        if (doctor == null || !doctor.filledNameAndSurname()){
            coreErrors.add(new CoreError("doctor", "Not valid input for doctor"));
        } else {
            coreErrors.addAll(validateDoctorsPersonalData(doctor));
        }
        return coreErrors;
    }

    private List<CoreError> validateDoctorsPersonalData(Doctor doctor) {
        List<CoreError> errors = new ArrayList<>();
        if (!doctor.getName().matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")){
            errors.add(new CoreError("doctor's name", "Name can contain only letters"));
        }
        if (!doctor.getSurname().matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")){
            errors.add(new CoreError("doctor's surname", "Surname can contain only letters"));
        }
        return errors;
    }
}
