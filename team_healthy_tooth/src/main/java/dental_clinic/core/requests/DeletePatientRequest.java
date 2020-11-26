package dental_clinic.core.requests;

public class DeletePatientRequest {

    private Long id;

    public DeletePatientRequest(Long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

}
