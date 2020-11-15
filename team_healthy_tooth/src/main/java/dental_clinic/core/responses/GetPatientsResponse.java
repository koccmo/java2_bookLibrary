package dental_clinic.core.responses;

import dental_clinic.core.domain.Patient;

import java.util.List;

public class GetPatientsResponse extends CoreResponse{

    private List<Patient> patients;

    public GetPatientsResponse(List<Patient> patients) {
        this.patients = patients;
    }

    public GetPatientsResponse(List<CoreError> errors, List<Patient> patients) {
        super(errors);
        this.patients = patients;
    }


    public List<Patient>getPatients(){
        return patients;
    }

}