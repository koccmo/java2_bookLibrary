package dental_clinic.core.responses.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class AddPatientResponse extends CoreResponse {

    private Patient newPatient;

    public AddPatientResponse(List<CoreError> errors){
        super(errors);
    }

    public AddPatientResponse(Patient newPatient){
        this.newPatient = newPatient;
    }

    public Patient getNewPatient(){
        return newPatient;
    }

}
