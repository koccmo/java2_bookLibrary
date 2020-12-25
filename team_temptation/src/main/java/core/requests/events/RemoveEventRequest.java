package core.requests.events;

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
}
