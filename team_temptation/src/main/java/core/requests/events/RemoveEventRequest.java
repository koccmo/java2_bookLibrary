package core.requests.events;

public class RemoveEventRequest {

    private final String eventName;

    public RemoveEventRequest(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

}
