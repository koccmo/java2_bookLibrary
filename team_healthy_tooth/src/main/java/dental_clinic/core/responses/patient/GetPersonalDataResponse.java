package dental_clinic.core.responses.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class GetPersonalDataResponse extends CoreResponse {

    private Patient patient;

    public GetPersonalDataResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public GetPersonalDataResponse(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }
}
