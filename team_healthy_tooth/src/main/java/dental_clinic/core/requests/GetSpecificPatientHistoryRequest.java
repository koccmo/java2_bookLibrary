package dental_clinic.core.requests;

public class GetSpecificPatientHistoryRequest {

    private Long id;

    public GetSpecificPatientHistoryRequest(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

}
