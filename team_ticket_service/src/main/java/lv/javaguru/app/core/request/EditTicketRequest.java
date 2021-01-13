package lv.javaguru.app.core.request;


public class EditTicketRequest {

    private final Long id;

    public EditTicketRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
