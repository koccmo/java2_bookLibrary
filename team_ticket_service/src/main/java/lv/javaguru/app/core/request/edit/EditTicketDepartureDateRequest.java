package lv.javaguru.app.core.request.edit;

public class EditTicketDepartureDateRequest {
    private final String departureDate;
    private final Long id;

    public EditTicketDepartureDateRequest(Long id, String departureDate) {
        this.id = id;
        this.departureDate = departureDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public Long getId() {
        return id;
    }
}