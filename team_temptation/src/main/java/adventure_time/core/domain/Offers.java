package adventure_time.core.domain;

import java.util.Date;
import java.util.Objects;

public class Offers {

    private long offerId;
    private Events event;
    private int eventRating;
    private Date startDate;
    private double eventCoast;
    private Discounts discount;
    private boolean isComplete;

    public Offers(long offerId, Events event, int eventRating, Date startDate, double eventCoast, Discounts discount, boolean isComplete) {
        this.event = event;
        this.eventRating = eventRating;
        this.startDate = startDate;
        this.eventCoast = eventCoast;
        this.discount = discount;
        this.isComplete = isComplete;
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public int getEventRating() {
        return eventRating;
    }

    public void setEventRating(int eventRating) {
        this.eventRating = eventRating;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getEventCoast() {
        return eventCoast;
    }

    public void setEventCoast(double eventCoast) {
        this.eventCoast = eventCoast;
    }

    public Discounts getDiscount() {
        return discount;
    }

    public void setDiscount(Discounts discount) {
        this.discount = discount;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offers offers = (Offers) o;
        return offerId == offers.offerId &&
                eventRating == offers.eventRating &&
                Double.compare(offers.eventCoast, eventCoast) == 0 &&
                isComplete == offers.isComplete &&
                Objects.equals(event, offers.event) &&
                Objects.equals(startDate, offers.startDate) &&
                Objects.equals(discount, offers.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId, event, eventRating, startDate, eventCoast, discount, isComplete);
    }

    @Override
    public String toString() {
        return "Offers{" +
                "id=" + offerId +
                ", event=" + event.getEventName() +
                ", rating=" + eventRating +
                ", date=" + startDate +
                ", coast=" + eventCoast +
                ", discount=" + discount +
                ", complete=" + isComplete +
                '}';
    }
}
