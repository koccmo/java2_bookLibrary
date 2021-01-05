package dental_clinic.core.responses.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class GetPatientCardResponse extends CoreResponse {

    private Patient patient;

    public GetPatientCardResponse(Patient patient) {
        this.patient = patient;
    }

    public GetPatientCardResponse(List<CoreError> errors) {
        super(errors);
    }

    public Patient getPatient() {
        return patient;
    }
}
