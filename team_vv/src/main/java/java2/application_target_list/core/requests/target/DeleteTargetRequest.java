package java2.application_target_list.core.requests.target;

public class DeleteTargetRequest {

    private Long targetIdToDelete;

    public DeleteTargetRequest() {
    }

    public void setTargetIdToDelete(Long targetIdToDelete) {
        this.targetIdToDelete = targetIdToDelete;
    }

    public DeleteTargetRequest(Long targetIdToDelete) {
        this.targetIdToDelete = targetIdToDelete;
    }

    public Long getTargetIdToDelete() {
        return targetIdToDelete;
    }
}
