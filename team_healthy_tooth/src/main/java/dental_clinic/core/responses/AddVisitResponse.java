package dental_clinic.core.responses;

import java.util.List;

public class AddVisitResponse extends CoreResponse{

    public AddVisitResponse(List<CoreError> errors){
        super(errors);
    }

    public AddVisitResponse(){}

}
