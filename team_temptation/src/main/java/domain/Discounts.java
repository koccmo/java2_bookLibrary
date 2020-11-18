package domain;

import java.util.Date;
import java.util.Objects;

public class Discounts {

    private long discountId;
    private String discountKind;
    private double discountAmount; // %
    private Date discountStartDate;
    private Date discountStopDate;

    public Discounts(String discountKind, double discountAmount, Date discountStartDate, Date discountStopDate) {
        this.discountKind = discountKind;
        this.discountAmount = discountAmount;
        this.discountStartDate = discountStartDate;
        this.discountStopDate = discountStopDate;
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public String getDiscountKind() {
        return discountKind;
    }

    public void setDiscountKind(String discountKind) {
        this.discountKind = discountKind;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Date getDiscountStartDate() {
        return discountStartDate;
    }

    public void setDiscountStartDate(Date discountStartDate) {
        this.discountStartDate = discountStartDate;
    }

    public Date getDiscountStopDate() {
        return discountStopDate;
    }

    public void setDiscountStopDate(Date discountStopDate) {
        this.discountStopDate = discountStopDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discounts discounts = (Discounts) o;
        return discountId == discounts.discountId &&
                Double.compare(discounts.discountAmount, discountAmount) == 0 &&
                Objects.equals(discountKind, discounts.discountKind) &&
                Objects.equals(discountStartDate, discounts.discountStartDate) &&
                Objects.equals(discountStopDate, discounts.discountStopDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountId, discountKind, discountAmount, discountStartDate, discountStopDate);
    }

    @Override
    public String toString() {
        return "Discounts{" +
                "id=" + discountId +
                ", kind='" + discountKind + '\'' +
                ", amount=" + discountAmount +
                ", start date=" + discountStartDate +
                ", stop date=" + discountStopDate +
                '}';
    }
}
