package domain;

import java.util.Objects;

public class Event {

    private long idNumber;
    private String eventName;
    private String startDate;
    private String finishDate;
    private String detailsDescription;

    public Event(String eventName, String startDate, String finishDate, String detailsDescription) {
        this.eventName = eventName;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.detailsDescription = detailsDescription;
    }

    public void setIdNumber(long idNumber) {
        this.idNumber = idNumber;
    }

    public long getIdNumber() {
        return idNumber;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
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
        Event event = (Event) o;
        return idNumber == event.idNumber &&
                Objects.equals(eventName, event.eventName) &&
                Objects.equals(startDate, event.startDate) &&
                Objects.equals(finishDate, event.finishDate) &&
                Objects.equals(detailsDescription, event.detailsDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNumber, eventName, startDate, finishDate, detailsDescription);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "idNumber=" + idNumber +
                ", eventName='" + eventName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", finishDate='" + finishDate + '\'' +
                ", detailsDescription='" + detailsDescription + '\'' +
                '}';
    }
}
