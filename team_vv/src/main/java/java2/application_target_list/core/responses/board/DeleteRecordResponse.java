package java2.application_target_list.core.responses.board;

import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class DeleteRecordResponse extends RecordCoreResponse {

    private Long recordIdToDelete;

    public DeleteRecordResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public DeleteRecordResponse(Long recordIdToDelete) {
        this.recordIdToDelete = recordIdToDelete;
    }

    public Long getRecordIdToDelete() {
        return recordIdToDelete;
    }
}
