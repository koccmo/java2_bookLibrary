package lv.javaguru.app.core.request.edit;

public class EditTicketDestinationRequest {
    private final String destinationCity;
    private final Long id;

    public EditTicketDestinationRequest(Long id, String destinationCity) {
        this.id = id;
        this.destinationCity = destinationCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public Long getId() {
        return id;
    }
}
