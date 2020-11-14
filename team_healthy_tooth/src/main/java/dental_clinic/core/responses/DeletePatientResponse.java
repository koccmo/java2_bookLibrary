package dental_clinic.core.responses;

import dental_clinic.core.domain.Patient;

import java.util.List;

public class DeletePatientResponse extends CoreResponse{

    private long id;

    public DeletePatientResponse(List<CoreError> errors){
        super(errors);
    }

    public DeletePatientResponse(long id){
        this.id = id;
    }

    public long getDeletedPatient(){
        return id;
    }

}
