package dental_clinic.core.responses;

import dental_clinic.core.domain.Patient;

import java.util.List;

public class FindPatientByPersonalCodeResponse extends CoreResponse {

    private Patient foundPatient;

    public FindPatientByPersonalCodeResponse(List<CoreError> errors) {
        super(errors);
    }

    public FindPatientByPersonalCodeResponse(Patient foundPatient) {
        this.foundPatient = foundPatient;
    }

    public Patient getFoundPatient() {
        return this.foundPatient;
    }

}
