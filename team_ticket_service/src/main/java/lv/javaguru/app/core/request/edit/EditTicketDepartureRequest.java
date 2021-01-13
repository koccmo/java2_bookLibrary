package lv.javaguru.app.core.request.edit;

public class EditTicketDepartureRequest {
    private final String departureCity;
    private final Long id;

    public EditTicketDepartureRequest(Long id, String departureCity) {
        this.id = id;
        this.departureCity = departureCity;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public Long getId() {
        return id;
    }
}
