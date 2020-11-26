package dental_clinic.core.responses;

import java.util.List;

public class CheckPatientByIdResponse extends CoreResponse {

    private Long id;

    public CheckPatientByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public CheckPatientByIdResponse(Long id){
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

}
