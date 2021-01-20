package lv.javaguru.app.core.request;


public class EditReservationRequest {

    private final Long id;

    public EditReservationRequest (Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
