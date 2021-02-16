package internet_store.application.core.requests.customer;

public class UpdateCustomerRequest {
    private Long id;
    private String newFirstName;
    private String newSecondName;
    private String newPhone;
    private String newEMail;
    private String newAddress;

    public UpdateCustomerRequest() { }

    public Long getId() {
        return id;
    }

    public String getNewFirstName() {
        return newFirstName;
    }

    public String getNewSecondName() {
        return newSecondName;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public String getNewEMail() {
        return newEMail;
    }

    public String getNewAddress() {
        return newAddress;
    }
}
