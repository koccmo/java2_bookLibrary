package lv.javaguru.app.core.request;

public class DeleteReservationRequest {

    private final Long id;

    public DeleteReservationRequest(Long id) {
        this.id = id;
    }

    public Long getId () {
        return id;
    }
}
