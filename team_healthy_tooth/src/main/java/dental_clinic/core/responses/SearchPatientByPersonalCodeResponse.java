package dental_clinic.core.responses;

import dental_clinic.core.domain.Patient;

import java.util.List;

public class SearchPatientByPersonalCodeResponse extends CoreResponse {

    private Patient foundPatient;

    public SearchPatientByPersonalCodeResponse(List<CoreError> errors) {
        super(errors);
    }

    public SearchPatientByPersonalCodeResponse(Patient foundPatient) {
        this.foundPatient = foundPatient;
    }

    public Patient getFoundPatient() {
        return this.foundPatient;
    }

}
