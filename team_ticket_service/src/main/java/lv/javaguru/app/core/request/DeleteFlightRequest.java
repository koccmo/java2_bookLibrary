package lv.javaguru.app.core.request;

public class DeleteFlightRequest {

    private final Long id;

    public DeleteFlightRequest (Long id) {
        this.id = id;
    }

    public Long getId () {
        return id;
    }
}
