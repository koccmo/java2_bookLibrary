package dental_clinic.core.requests.manipulation;

public class DeactivateManipulationRequest {

    private Long id;

    public DeactivateManipulationRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
