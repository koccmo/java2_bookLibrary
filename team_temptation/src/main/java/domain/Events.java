package domain;

import java.util.Objects;

public class Events {

    private long eventId;
    private int eventRating;
    private String eventName;
    private String eventKind;
//    private Guides guide;
    private int durationHours;
    private int maxNumberParticipants;
    private int minNumberParticipants;
    private String route; // List<StayPoint>

    private String detailsDescription;

    public Events(String eventName, String eventKind, int durationHours,
                  int maxNumberParticipants, int minNumberParticipants,
                  String route, String detailsDescription) {
        this.eventName = eventName;
        this.eventKind = eventKind;
        this.durationHours = durationHours;
        this.maxNumberParticipants = maxNumberParticipants;
        this.minNumberParticipants = minNumberParticipants;
        this.route = route;
        this.detailsDescription = detailsDescription;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventKind() {
        return eventKind;
    }

    public void setEventKind(String eventKind) {
        this.eventKind = eventKind;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    public int getMaxNumberParticipants() {
        return maxNumberParticipants;
    }

    public void setMaxNumberParticipants(int maxNumberParticipants) {
        this.maxNumberParticipants = maxNumberParticipants;
    }

    public int getMinNumberParticipants() {
        return minNumberParticipants;
    }

    public void setMinNumberParticipants(int minNumberParticipants) {
        this.minNumberParticipants = minNumberParticipants;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getEventRating() {
        return eventRating;
    }

    public void setEventRating(int eventRating) {
        this.eventRating = eventRating;
    }

    public String getDetailsDescription() {
        return detailsDescription;
    }

    public void setDetailsDescription(String detailsDescription) {
        this.detailsDescription = detailsDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Events events = (Events) o;
        return eventId == events.eventId &&
                durationHours == events.durationHours &&
                maxNumberParticipants == events.maxNumberParticipants &&
                minNumberParticipants == events.minNumberParticipants &&
                eventRating == events.eventRating &&
                Objects.equals(eventName, events.eventName) &&
                Objects.equals(eventKind, events.eventKind) &&
                Objects.equals(route, events.route) &&
                Objects.equals(detailsDescription, events.detailsDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, eventKind, durationHours, maxNumberParticipants, minNumberParticipants, route, eventRating, detailsDescription);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + eventId +
                ", route='" + route +
                ", event='" + eventName +
                ", kind='" + eventKind +
                ", duration=" + durationHours + " hours" +
                ", maxNumber=" + maxNumberParticipants +
                ", minNumber=" + minNumberParticipants +
                ", rating=" + eventRating +
                ", details='" + detailsDescription +
                '}';
    }
}
