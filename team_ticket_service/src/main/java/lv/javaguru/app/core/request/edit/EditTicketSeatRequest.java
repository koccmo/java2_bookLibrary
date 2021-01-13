package lv.javaguru.app.core.request.edit;

public class EditTicketSeatRequest {

    private final String seat;
    private final Long id;

    public EditTicketSeatRequest(Long id, String seat) {
        this.id = id;
        this.seat = seat;
    }

    public String getSeat() {
        return seat;
    }

    public Long getId() {
        return id;
    }
}
