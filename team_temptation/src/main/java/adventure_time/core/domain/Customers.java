package adventure_time.core.domain;

import java.util.Objects;

public class Customers {

    private long customerID;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private int customerRating;

    public Customers(long customerID, String customerName, String customerEmail, String customerPhone, int customerRating) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerRating = customerRating;
    }

    public long getcustomerID() {
        return customerID;
    }

    public void setcustomerID(long customerID) {
        this.customerID = customerID;
    }

    public String getcustomerName() {
        return customerName;
    }

    public void setcustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getcustomerEmail() {
        return customerEmail;
    }

    public void setcustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getcustomerPhone() {
        return customerPhone;
    }

    public void setcustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getcustomerRating() {
        return customerRating;
    }

    public void setcustomerRating(int customerRating) {
        this.customerRating = customerRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return customerID == customers.customerID &&
                customerRating == customers.customerRating &&
                Objects.equals(customerName, customers.customerName) &&
                Objects.equals(customerEmail, customers.customerEmail) &&
                Objects.equals(customerPhone, customers.customerPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, customerName, customerEmail, customerPhone, customerRating);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerRating=" + customerRating +
                '}';
    }
}
