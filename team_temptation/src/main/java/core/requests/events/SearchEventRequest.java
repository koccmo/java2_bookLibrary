package core.requests.events;

import core.requests.Ordering;
import core.requests.Paging;

import java.util.Objects;

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchEventRequest that = (SearchEventRequest) o;
        return Objects.equals(eventKind, that.eventKind) &&
                Objects.equals(route, that.route) &&
                Objects.equals(durationHours, that.durationHours) &&
                Objects.equals(ordering, that.ordering) &&
                Objects.equals(paging, that.paging);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventKind, route, durationHours, ordering, paging);
    }

}
