package adventure_time.core.requests.events;

import java.util.Objects;

public class UpdateEventRequest {

    private final Long eventId;

    public UpdateEventRequest(Long eventId) {
        this.eventId = eventId;
    }

    public Long getEventId() {
        return eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateEventRequest that = (UpdateEventRequest) o;
        return Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }
}
