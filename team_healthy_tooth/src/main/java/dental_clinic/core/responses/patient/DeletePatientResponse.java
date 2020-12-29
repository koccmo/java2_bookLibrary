package dental_clinic.core.responses.patient;


import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class DeletePatientResponse extends CoreResponse {

    private Long id;

    public DeletePatientResponse(List<CoreError> errors){
        super(errors);
    }

    public DeletePatientResponse(Long id){
        this.id = id;
    }

    public long getDeletedPatient(){
        return id;
    }

}
