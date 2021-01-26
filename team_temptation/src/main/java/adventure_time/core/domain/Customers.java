package adventure_time.core.domain;

import java.util.Objects;

public class Customers {

    private Long customerID;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String customerPassword;
    private Boolean activity;

    public Customers(String customerName, String customerEmail, String customerPhone, String customerPassword) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerPassword = customerPassword;
        this.activity = true;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Boolean getActivity() {
        return activity;
    }

    public void setActivity(Boolean activity) {
        this.activity = activity;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return Objects.equals(customerID, customers.customerID) &&
                Objects.equals(customerName, customers.customerName) &&
                Objects.equals(customerEmail, customers.customerEmail) &&
                Objects.equals(customerPhone, customers.customerPhone) &&
                Objects.equals(customerPassword, customers.customerPassword) &&
                Objects.equals(activity, customers.activity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, customerName, customerEmail, customerPhone, customerPassword, activity);
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customerID=" + customerID +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerPassword='" + customerPassword + '\'' +
                ", activity=" + activity +
                '}';
    }
}
