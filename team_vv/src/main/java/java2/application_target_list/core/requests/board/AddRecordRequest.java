package java2.application_target_list.core.requests.board;

public class AddRecordRequest {

    private Long targetId;
    private Long userId;

    public AddRecordRequest() {
    }

    public AddRecordRequest(Long targetId, Long userId) {
        this.targetId = targetId;
        this.userId = userId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
