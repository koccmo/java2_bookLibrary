package adventure_time.core.domain;

import java.util.Date;
import java.util.Objects;

import static java.lang.Long.valueOf;

public class Tours {

    private final static Long TWELVE_HOURS = 43200000L;

    private Long id;
    private Date creationDate;
    private Events event;       // foreign key
    private Date eventDate;
    private Double eventCoast;
    private Integer ticketAmount;

    public Tours(Events event, Date eventDate, Double eventCoast) {
        this.event = event;
        this.eventDate = eventDate;
        this.eventCoast = eventCoast;
        this.ticketAmount = 0;
    }

    public boolean isComplete() {
        return event.getMaxNumberParticipants().equals(ticketAmount);
    }

    public boolean isComing() {
        Date now = new Date();
        return (eventDate.getTime() - now.getTime()) < TWELVE_HOURS;
    }

    public void incTicketAmount() {
        this.ticketAmount++;
    }



    //*******************************************

    public void setTourId(Long id) {
        this.id = id;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getTourId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Events getLinkedEvent() {
        return event;
    }

    public Date getLinkedEventDate() {
        return eventDate;
    }

    public Double getLinkedEventCoast() {
        return eventCoast;
    }

    public Integer getTicketAmount() {
        return ticketAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tours tours = (Tours) o;
        return Objects.equals(id, tours.id) &&
                Objects.equals(creationDate, tours.creationDate) &&
                Objects.equals(event, tours.event) &&
                Objects.equals(eventDate, tours.eventDate) &&
                Objects.equals(eventCoast, tours.eventCoast) &&
                Objects.equals(ticketAmount, tours.ticketAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, event, eventDate, eventCoast, ticketAmount);
    }

    @Override
    public String toString() {
        return "Tours{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", event=" + event +
                ", eventDate=" + eventDate +
                ", eventCoast=" + eventCoast +
                ", ticketAmount=" + ticketAmount +
                '}';
    }
}
