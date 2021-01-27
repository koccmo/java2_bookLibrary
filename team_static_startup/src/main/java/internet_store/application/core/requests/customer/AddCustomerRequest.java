package internet_store.application.core.requests.customer;

public class AddCustomerRequest {

    private String customerFirstName;
    private String customerSecondName;
    private String customerPhone;
    private String customerEMail;
    private String customerAddress;

    public AddCustomerRequest(String customerFirstName, String customerSecondName, String customerPhone) {
        this.customerFirstName = customerFirstName;
        this.customerSecondName = customerSecondName;
        this.customerPhone = customerPhone;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerSecondName() {
        return customerSecondName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getCustomerEMail() {
        return customerEMail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setCustomerEMail(String customerEMail) {
        this.customerEMail = customerEMail;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}
