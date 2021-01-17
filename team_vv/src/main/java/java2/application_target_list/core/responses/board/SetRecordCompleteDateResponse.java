package java2.application_target_list.core.responses.board;

import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class SetRecordCompleteDateResponse extends RecordCoreResponse{

    private Long recordIdToSetCompleteDate;

    public SetRecordCompleteDateResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public SetRecordCompleteDateResponse(Long recordIdToSetCompleteDate) {
        this.recordIdToSetCompleteDate = recordIdToSetCompleteDate;
    }

    public Long getRecordIdToSetCompleteDate() {
        return recordIdToSetCompleteDate;
    }
}
