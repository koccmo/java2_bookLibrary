package dental_clinic.core.responses.manipulation;

import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class DeactivateManipulationResponse extends CoreResponse {

    private Long id;

    public DeactivateManipulationResponse(Long id) {
        this.id = id;
    }

    public DeactivateManipulationResponse(List<CoreError> errors) {
        super(errors);
    }

    public Long getId() {
        return id;
    }
}
