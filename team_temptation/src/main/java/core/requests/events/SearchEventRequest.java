package core.requests.events;

import core.requests.Ordering;
import core.requests.Paging;

public class SearchEventRequest {

    private String eventKind;
    private String route;
    private Integer durationHours;

    private Ordering ordering;

    private Paging paging;

    public SearchEventRequest(String eventKind, String route, Integer durationHours, Ordering ordering, Paging paging) {
        this.eventKind = eventKind;
        this.route = route;
        this.durationHours = durationHours;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getEventKind() {
        return eventKind;
    }

    public String getRoute() {
        return route;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    public boolean isKind() {
        return (!getEventKind().equals("")) && (getDurationHours().equals(0)) && (getRoute().equals(""));
    }
    public boolean isDuration() {
        return (getEventKind().equals("")) && (!getDurationHours().equals(0)) && (getRoute().equals(""));
    }
    public boolean isRoute() {
        return (getEventKind().equals("")) && (getDurationHours().equals(0)) && (!getRoute().equals(""));
    }
    public boolean isKindAndDuration() {
        return (!getEventKind().equals("")) && (!getDurationHours().equals(0)) && (getRoute().equals(""));
    }
    public boolean isKindAndRoute() {
        return (!getEventKind().equals("")) && (getDurationHours().equals(0)) && (!getRoute().equals(""));
    }
    public boolean isKindAndDurationAndRout() {
        return !(getEventKind().equals("")) && (!getDurationHours().equals(0)) && (!getRoute().equals(""));
    }
    public boolean isDurationAndRoute() {
        return (getEventKind().equals("")) && (!getDurationHours().equals(0)) && (!getRoute().equals(""));
    }
    public boolean isNoOne() {
        return (getEventKind().equals("")) && (getDurationHours().equals(0)) && (getRoute().equals(""));
    }
}
