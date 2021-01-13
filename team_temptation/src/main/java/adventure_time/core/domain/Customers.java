package adventure_time.core.domain;

import java.util.Objects;

public class Customers {

    private Long customerID;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private boolean activity;

    public Customers(String customerName, String customerEmail, String customerPhone) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.activity = true;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getCustomerId() {
        return customerID;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public String getCustomerPhone() {
        return customerPhone;
    }

    public boolean isActivity() {
        return activity;
    }
    public void activityOn () { this.activity = true;
    }
    public void activityOff () { this.activity = false; }

    @Override
    public String toString() {
        return "Customers{" +
                "customerID=" + customerID +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", activity=" + activity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return activity == customers.activity &&
                Objects.equals(customerID, customers.customerID) &&
                Objects.equals(customerName, customers.customerName) &&
                Objects.equals(customerEmail, customers.customerEmail) &&
                Objects.equals(customerPhone, customers.customerPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, customerName, customerEmail, customerPhone, activity);
    }
}
