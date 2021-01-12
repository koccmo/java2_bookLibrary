package adventure_time.core.domain;

import java.util.Date;
import java.util.Objects;

import static java.lang.Long.valueOf;

public class Offers {

    private final static Long TWELVE_HOURS = 43200000L;

    private Long offerId;
    private Date creationDate;
    private Events event;       // foreign key
    private Date eventDate;
    private Double eventCoast;
    private Integer ticketAmount;

    public Offers(Events event, Date eventDate, Double eventCoast) {
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

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getOfferId() {
        return offerId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Events getEvent() {
        return event;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public Double getEventCoast() {
        return eventCoast;
    }

    public Integer getTicketAmount() {
        return ticketAmount;
    }

    @Override
    public String toString() {
        return "Offers{" +
                "offerId=" + offerId +
                ", creationDate=" + creationDate +
                ", event=" + event +
                ", eventDate=" + eventDate +
                ", eventCoast=" + eventCoast +
                ", ticketAmount=" + ticketAmount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offers offers = (Offers) o;
        return Objects.equals(offerId, offers.offerId) &&
                Objects.equals(creationDate, offers.creationDate) &&
                Objects.equals(event, offers.event) &&
                Objects.equals(eventDate, offers.eventDate) &&
                Objects.equals(eventCoast, offers.eventCoast) &&
                Objects.equals(ticketAmount, offers.ticketAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId, creationDate, event, eventDate, eventCoast, ticketAmount);
    }
}
