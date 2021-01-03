package core.requests.events;

import java.util.Objects;

public class RemoveEventRequest {

    private final String eventName;
    private final Long eventId;
    private final String deletionWay;

    public RemoveEventRequest(String eventName, String deletionWay) {
        this.eventName = eventName;
        this.eventId = null;
        this.deletionWay = deletionWay;
    }

    public RemoveEventRequest(Long eventId, String deletionWay) {
        this.eventName = null;
        this.eventId = eventId;
        this.deletionWay = deletionWay;

    }

    public String getEventName() {
        return eventName;
    }

    public String getDeletionWay() {
        return deletionWay;
    }

    public Long getEventId() {
        return eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveEventRequest request = (RemoveEventRequest) o;
        return Objects.equals(eventName, request.eventName) &&
                Objects.equals(eventId, request.eventId) &&
                Objects.equals(deletionWay, request.deletionWay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, eventId, deletionWay);
    }
}
