package dental_clinic.core.responses.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class GetAllPatientsResponse extends CoreResponse {

    private List<Patient> patients;

    public GetAllPatientsResponse(List<Patient> patients) {
        this.patients = patients;
    }

    public GetAllPatientsResponse(List<CoreError> errors, List<Patient> patients) {
        super(errors);
        this.patients = patients;
    }


    public List<Patient>getPatients(){
        return patients;
    }

}