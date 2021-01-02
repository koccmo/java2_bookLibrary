//package matchers;
//
//import domain.Events;
//import org.mockito.ArgumentMatcher;
//
//public class EventMatcher implements ArgumentMatcher<Events> {
//    private Long eventId;
//    private String eventName;
//    private String eventKind;
//    //    private Guides guide;
//    private Integer durationHours;
//    private Integer maxNumberParticipants;
//    private Integer minNumberParticipants;
//    private String route; // List<StayPoint>
//    private String detailsDescription;
//
//
//    public EventMatcher (Events event) {
//        this.eventId = event.getEventId();
//        this.eventName = event.getEventName();
//        this.eventKind = event.getEventKind();
//        this.durationHours = event.getDurationHours();
//        this.maxNumberParticipants = event.getMaxNumberParticipants();
//        this.minNumberParticipants = event.getMinNumberParticipants();
//        this.route = event.getRoute();
//        this.detailsDescription = event.getDetailsDescription();
//    }
//
//    @Override
//    public boolean matches(Events event) {
//        return event.getEventId().equals(eventId) &&
//                event.getEventName().equals(eventName) &&
//                event.getEventKind().equals(eventKind) &&
//                event.getDurationHours().equals(durationHours) &&
//                event.getMaxNumberParticipants().equals(maxNumberParticipants) &&
//                event.getMinNumberParticipants().equals(minNumberParticipants) &&
//                event.getRoute().equals(route) &&
//                event.getDetailsDescription().equals(detailsDescription);
//    }
//}
