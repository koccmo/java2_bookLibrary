package lv.javaguru.app.core.request.edit;

import java.time.LocalDate;

public class EditTicketDepartureDateRequest {
    private final LocalDate departureDate;
    private final Long id;

    public EditTicketDepartureDateRequest(Long id, LocalDate departureDate) {
        this.id = id;
        this.departureDate = departureDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public Long getId() {
        return id;
    }
}