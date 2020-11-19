package application_target_list.core.requests;

public class DeleteTargetRequest {

    private Long targetIdToDelete;

    public DeleteTargetRequest(Long targetIdToDelete) {
        this.targetIdToDelete = targetIdToDelete;
    }

    public Long getTargetIdToDelete() {
        return targetIdToDelete;
    }
}
