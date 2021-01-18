package dental_clinic.core.responses.manipulation;

import dental_clinic.core.domain.Manipulation;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class GetManipulationsListResponse extends CoreResponse {

    List <Manipulation> manipulationList;

    public GetManipulationsListResponse(List<Manipulation> manipulationList) {
        this.manipulationList = manipulationList;
    }

    public GetManipulationsListResponse(List<CoreError> errors, List<Manipulation> manipulationList) {
        super(errors);
    }

    public List<Manipulation> getManipulationList() {
        return manipulationList;
    }
}
