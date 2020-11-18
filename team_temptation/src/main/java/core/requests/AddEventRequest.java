package core.requests;

public class AddEventRequest {

    private String eventName;
    private String startDate;
    private String finishDate;
    private String detailDescription;

    public AddEventRequest(String eventName, String startDate, String finishDate, String detailDescription) {
        this.eventName = eventName;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.detailDescription = detailDescription;
    }

    public String getEventName() {
        return eventName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

}
