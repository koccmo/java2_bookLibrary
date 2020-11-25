package internet_store.core.requests;

public class DeleteTargetRequest {

    private Long targetIdToDelete;

    public DeleteTargetRequest(Long targetIdToDelete) {
        this.targetIdToDelete = targetIdToDelete;
    }

    public Long getTargetIdToDelete() {
        return targetIdToDelete;
    }
}
