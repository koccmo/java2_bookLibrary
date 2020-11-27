package dental_clinic.core.responses;

import dental_clinic.core.domain.Patient;

import java.util.List;

public class GetPatientCardResponse extends CoreResponse{

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
