package lesson_3.core.responses;

import java.util.List;

public class DeleteTargetResponse extends CoreResponse {

    private Long targetIdToDelete;

    public DeleteTargetResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public DeleteTargetResponse(Long targetIdToDelete) {
        this.targetIdToDelete = targetIdToDelete;
    }

    public Long getTargetIdToDelete() {
        return targetIdToDelete;
    }
}
