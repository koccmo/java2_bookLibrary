package java2.application_target_list.core.responses.target;

import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class DeleteTargetResponse extends TargetCoreResponse {

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
