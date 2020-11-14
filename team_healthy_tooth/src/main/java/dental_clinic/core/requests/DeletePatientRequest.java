package dental_clinic.core.requests;

public class DeletePatientRequest {

    private long id;

    public DeletePatientRequest(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

}
