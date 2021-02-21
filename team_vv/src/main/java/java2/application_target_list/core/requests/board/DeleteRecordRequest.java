package java2.application_target_list.core.requests.board;

public class DeleteRecordRequest {

    private Long recordIdToDelete;

    public DeleteRecordRequest() {
    }

    public DeleteRecordRequest(Long recordIdToDelete) {
        this.recordIdToDelete = recordIdToDelete;
    }

    public Long getRecordIdToDelete() {
        return recordIdToDelete;
    }

    public void setRecordIdToDelete(Long recordIdToDelete) {
        this.recordIdToDelete = recordIdToDelete;
    }
}
