package core.requests.events;

public class AddEventRequest {

    private String eventName;
    private String eventKind;
    //    private Guides guide;
    private int durationHours;
    private int maxNumberParticipants;
    private int minNumberParticipants;
    private String route; // List<StayPoint>

    private String detailsDescription;

    public AddEventRequest(String eventName, String eventKind, int durationHours,
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

    public String getEventName() {
        return eventName;
    }

    public String getEventKind() {
        return eventKind;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public int getMaxNumberParticipants() {
        return maxNumberParticipants;
    }

    public int getMinNumberParticipants() {
        return minNumberParticipants;
    }

    public String getRoute() {
        return route;
    }

    public String getDetailsDescription() {
        return detailsDescription;
    }
}
