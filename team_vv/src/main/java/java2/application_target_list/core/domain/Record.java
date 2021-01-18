package java2.application_target_list.core.domain;

public class Record {

    private Long recordId;
    private Long targetId;
    private Long userId;
    private String dateAdded;
    private String dateComplete;

    public Record(Long targetId, Long userId) {
        this.targetId = targetId;
        this.userId = userId;
    }



    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(String dateComplete) {
        this.dateComplete = dateComplete;
    }

    public String getDateAdded() {
        return dateAdded;
    }
}
