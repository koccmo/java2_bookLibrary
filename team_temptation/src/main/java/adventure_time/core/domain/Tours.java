package adventure_time.core.domain;

import java.util.Date;
import java.util.Objects;

public class Tours {

    private final static Long TWELVE_HOURS = 43200000L;

    private Long id;
    private Events event;       // foreign key
    private Date releaseDate;
    private Date startingDate;
    private Double ticketCoast;
    private Boolean completed;
    private Integer soldTickets;

    public Tours(Events event, Date releaseDate, Date startingDate, Double ticketCoast) {
        this.event = event;
        this.releaseDate = releaseDate;
        this.startingDate = startingDate;
        this.ticketCoast = ticketCoast;
    }

    public Boolean isCompleted() {
        return event.getMaxNumberParticipants().equals(soldTickets);
    }

    public Boolean isComing() {
        Date now = new Date();
        return (startingDate.getTime() - now.getTime()) < TWELVE_HOURS;
    }

    public Boolean incTicketAmount() {
        if (this.soldTickets >= event.getMaxNumberParticipants()) return false;
        this.soldTickets++;
        return true;
    }

    //*******************************************

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Double getTicketCoast() {
        return ticketCoast;
    }

    public void setTicketCoast(Double ticketCoast) {
        this.ticketCoast = ticketCoast;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Integer getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(Integer soldTickets) {
        this.soldTickets = soldTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tours tours = (Tours) o;
        return Objects.equals(id, tours.id) &&
                Objects.equals(event, tours.event) &&
                Objects.equals(releaseDate, tours.releaseDate) &&
                Objects.equals(startingDate, tours.startingDate) &&
                Objects.equals(ticketCoast, tours.ticketCoast) &&
                Objects.equals(completed, tours.completed) &&
                Objects.equals(soldTickets, tours.soldTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, event, releaseDate, startingDate, ticketCoast, completed, soldTickets);
    }

    @Override
    public String toString() {
        return "Tours{" +
                "id=" + id +
                ", event=" + event +
                ", releaseDate=" + releaseDate +
                ", startingDate=" + startingDate +
                ", ticketCoast=" + ticketCoast +
                ", completed=" + completed +
                ", soldTickets=" + soldTickets +
                '}';
    }
}
