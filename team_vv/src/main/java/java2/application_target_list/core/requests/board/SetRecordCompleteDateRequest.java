package java2.application_target_list.core.requests.board;

public class SetRecordCompleteDateRequest {

    private Long recordIdToSetCompleteDate;

    public SetRecordCompleteDateRequest(Long recordIdToSetCompleteDate) {
        this.recordIdToSetCompleteDate = recordIdToSetCompleteDate;
    }

    public Long getRecordIdToSetCompleteDate() {
        return recordIdToSetCompleteDate;
    }
}
