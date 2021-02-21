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

        coreErrors.addAll(jowlInfoValidationErrors(updatePatientsJowlInfoRequest));

        return coreErrors;
    }

    private List<CoreError> idValidationErrors(Long id) {
        List <CoreError> coreErrors = new ArrayList<>();
        if ((id == null) || (id < 1)){
            coreErrors.add(new CoreError("id", "Not valid input of id"));
        }
        return coreErrors;
    }

    private List<CoreError> jowlInfoValidationErrors (UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest) {
        List <CoreError> coreErrors = new ArrayList<>();
        if (updatePatientsJowlInfoRequest.getToothNumber() == null || updatePatientsJowlInfoRequest.getToothStatus() == null) {
            coreErrors.add(new CoreError("toothInfo", "Can't be empty!"));
            return coreErrors;
        }
        if (!isValidToothNumber(updatePatientsJowlInfoRequest.getToothNumber())) {
            coreErrors.add(new CoreError("tooth number", "Not valid tooth number " + updatePatientsJowlInfoRequest.getToothNumber()));
        }
        if (!isValidToothStatus(updatePatientsJowlInfoRequest.getToothStatus())) {
            coreErrors.add(new CoreError("tooth status", "Not valid tooth status for tooth number " + updatePatientsJowlInfoRequest.getToothStatus()));
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
        return toothStatus.equals(ToothStatus.KARIES) ||
                toothStatus.equals(ToothStatus.PLOMBA) ||
                toothStatus.equals(ToothStatus.SAKNE) ||
                toothStatus.equals(ToothStatus.KRONITIS) ||
                toothStatus.equals(ToothStatus.KLAMERS) ||
                toothStatus.equals(ToothStatus.NAV_ZOBA) ||
                toothStatus.equals(ToothStatus.FASETE) ||
                toothStatus.equals(ToothStatus.NONEMAMA_PROTEZE) ||
                toothStatus.equals(ToothStatus.KRONITIS_AR_FAS) ||
                toothStatus.equals(ToothStatus.PLAST_KRONITIS) ||
                toothStatus.equals(ToothStatus.TILTINI) ||
                toothStatus.equals(ToothStatus.HEALTHY) ||
                !toothStatus.equals(ToothStatus.OTHER);
    }

}
