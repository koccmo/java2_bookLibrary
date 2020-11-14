package dental_clinic.core.requests;

public class GetSpecificPatientHistoryRequest {

    private long id;

    public GetSpecificPatientHistoryRequest(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

}
