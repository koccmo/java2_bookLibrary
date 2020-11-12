package lesson_1;

import java.util.Objects;

public class Trip {

    private int idNumber;
    private String tripName;
    private String startDate;
    private String finishDate;
    private String detailsDescription;

    public Trip(String tripName, String startDate, String finishDate, String detailsDescription) {
        this.tripName = tripName;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.detailsDescription = detailsDescription;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
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
        Trip trip = (Trip) o;
        return idNumber == trip.idNumber &&
                Objects.equals(tripName, trip.tripName) &&
                Objects.equals(startDate, trip.startDate) &&
                Objects.equals(finishDate, trip.finishDate) &&
                Objects.equals(detailsDescription, trip.detailsDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNumber, tripName, startDate, finishDate, detailsDescription);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "idNumber=" + idNumber +
                ", tripName='" + tripName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", finishDate='" + finishDate + '\'' +
                ", detailsDescription='" + detailsDescription + '\'' +
                '}';
    }
}