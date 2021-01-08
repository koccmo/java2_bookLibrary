package dental_clinic.core.responses.visit;

import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class AddVisitResponse extends CoreResponse {

    public AddVisitResponse(List<CoreError> errors){
        super(errors);
    }

    public AddVisitResponse(){}

}
