package dental_clinic.core.validators.patient;

import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.requests.patient.UpdatePatientsJowlInfoRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UpdatePatientJowlInfoRequestValidator {

    public List<CoreError> validate (UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest) {
        List<CoreError> coreErrors = new ArrayList<>();

        coreErrors.addAll(idValidationErrors(updatePatientsJowlInfoRequest.getId()));

        coreErrors.addAll(jowlInfoValidationErrors(updatePatientsJowlInfoRequest.getJowlInfo()));

        return coreErrors;
    }

    private List<CoreError> idValidationErrors(Long id) {
        List <CoreError> coreErrors = new ArrayList<>();
        if ((id == null) || (id < 1)){
            coreErrors.add(new CoreError("id", "Not valid input of id"));
        }
        return coreErrors;
    }

    private List<CoreError> jowlInfoValidationErrors (Map<Integer, ToothStatus> jowlInfo) {
        List <CoreError> coreErrors = new ArrayList<>();
        for (Integer key : jowlInfo.keySet()) {
            if (!isValidToothNumber(key)) {
                coreErrors.add(new CoreError("tooth number", "Not valid tooth number " + key));
            }
            if (!isValidToothStatus(jowlInfo.get(key))) {
                coreErrors.add(new CoreError("tooth status", "Not valid tooth status for tooth number " + key));
            }
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

    private boolean isValidToothStatus (ToothStatus toothStatus) {
        return !toothStatus.equals(ToothStatus.OTHER);
    }

}
