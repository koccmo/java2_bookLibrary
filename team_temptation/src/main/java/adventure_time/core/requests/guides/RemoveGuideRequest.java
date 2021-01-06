package adventure_time.core.requests.guides;

public class RemoveGuideRequest {

    private final String eventName;

    public RemoveGuideRequest(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
