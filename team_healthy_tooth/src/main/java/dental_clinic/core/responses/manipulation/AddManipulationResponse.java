package dental_clinic.core.responses.manipulation;

import dental_clinic.core.domain.Manipulation;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class AddManipulationResponse extends CoreResponse {

    private Manipulation manipulation;

    public AddManipulationResponse(Manipulation manipulation) {
        this.manipulation = manipulation;
    }

    public AddManipulationResponse(List<CoreError> errors) {
        super(errors);
    }

    public Manipulation getManipulation() {
        return manipulation;
    }
}
