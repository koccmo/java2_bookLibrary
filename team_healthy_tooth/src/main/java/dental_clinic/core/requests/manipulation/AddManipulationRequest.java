package dental_clinic.core.requests.manipulation;

import dental_clinic.core.domain.Manipulation;

public class AddManipulationRequest {

    private Manipulation manipulation;

    public AddManipulationRequest() { }

    public AddManipulationRequest(Manipulation manipulation) {
        this.manipulation = manipulation;
    }

    public Manipulation getManipulation() {
        return manipulation;
    }

    public void setManipulation(Manipulation manipulation) {
        this.manipulation = manipulation;
    }
}
