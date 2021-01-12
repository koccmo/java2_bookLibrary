package adventure_time.core.requests.guides;

import java.util.Objects;

public class RemoveGuideRequest {

    private final String guideName;
    private final Long guideId;
    private final String deletionWay;

    public RemoveGuideRequest(String guideName, String deletionWay) {
        this.guideName = guideName;
        this.guideId = null;
        this.deletionWay = deletionWay;
    }
    public RemoveGuideRequest(Long guideId, String deletionWay) {
        this.guideName = null;
        this.guideId = guideId;
        this.deletionWay = deletionWay;
    }


    public String getGuideName() {
        return guideName;
    }

    public String getDeletionWay() {
        return deletionWay;
    }

    public Long getGuideId() {
        return guideId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveGuideRequest that = (RemoveGuideRequest) o;
        return Objects.equals(guideName, that.guideName) && Objects.equals(guideId, that.guideId) && Objects.equals(deletionWay, that.deletionWay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guideName, guideId, deletionWay);
    }
}
