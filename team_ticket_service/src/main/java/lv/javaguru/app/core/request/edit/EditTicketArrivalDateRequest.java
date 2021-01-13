package lv.javaguru.app.core.request.edit;

public class EditTicketArrivalDateRequest {

    private final String returnDate;
    private final Long id;

    public EditTicketArrivalDateRequest(Long id, String returnDate) {
        this.id = id;
        this.returnDate = returnDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public Long getId() {
        return id;
    }
}
