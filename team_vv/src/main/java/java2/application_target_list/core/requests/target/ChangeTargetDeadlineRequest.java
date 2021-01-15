package java2.application_target_list.core.requests.target;

public class ChangeTargetDeadlineRequest {

    private Long targetIdToChange;
    private Integer newTargetDeadline;

    public ChangeTargetDeadlineRequest(Long targetIdToChange, Integer newTargetDeadline) {
        this.targetIdToChange = targetIdToChange;
        this.newTargetDeadline = newTargetDeadline;
    }

    public Long getTargetIdToChange() {
        return targetIdToChange;
    }

    public Integer getNewTargetDeadline() {
        return newTargetDeadline;
    }
}
