package core.requests.events;

public class AddEventRequest {

    private String eventName;
    private String eventKind;
    //    private Guides guide;
    private Integer durationHours;
    private Integer maxNumberParticipants;
    private Integer minNumberParticipants;
    private String route; // List<StayPoint>

    private String detailsDescription;

    public AddEventRequest(String eventName, String eventKind, Integer durationHours,
                           Integer maxNumberParticipants, Integer minNumberParticipants,
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

    public Integer getDurationHours() {
        return durationHours;
    }

    public Integer getMaxNumberParticipants() {
        return maxNumberParticipants;
    }

    public Integer getMinNumberParticipants() {
        return minNumberParticipants;
    }

    public String getRoute() {
        return route;
    }

    public String getDetailsDescription() {
        return detailsDescription;
    }
}
