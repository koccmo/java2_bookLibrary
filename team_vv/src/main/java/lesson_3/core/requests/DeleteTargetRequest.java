package lesson_3.core.requests;

public class DeleteTargetRequest {

    private Long targetIdToDelete;

    public DeleteTargetRequest(Long targetIdToDelete) {
        this.targetIdToDelete = targetIdToDelete;
    }

    public Long getTargetIdToDelete() {
        return targetIdToDelete;
    }
}
