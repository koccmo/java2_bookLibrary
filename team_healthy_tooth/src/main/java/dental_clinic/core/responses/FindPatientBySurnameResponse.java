package dental_clinic.core.responses;

import dental_clinic.core.domain.Patient;

import java.util.List;

public class FindPatientBySurnameResponse extends CoreResponse{

    private List <Patient> specificPatients;

    public FindPatientBySurnameResponse(List<CoreError> errors, List <Patient> specificPatients){
        super(errors);
        this.specificPatients = specificPatients;
    }

    /* TODO Почему так не принимает?? :(((
    public FindPatientBySurnameResponse(List<CoreError> errors){
        super(errors);
    }

     */

    public FindPatientBySurnameResponse(List <Patient> specificPatients){
        this.specificPatients = specificPatients;
    }

    public List <Patient> getSpecificPatient(){
        return specificPatients;
    }

}
