package adventure_time.core.requests.customers;

import adventure_time.core.requests.Ordering;
import adventure_time.core.requests.Paging;

import java.util.Objects;

public class SearchCustomerRequest {

    private Long customerID;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private boolean activity;
    private Ordering ordering;
    private Paging paging;

    public SearchCustomerRequest(Long customerID, String customerName, String customerEmail, String customerPhone,
                                 boolean activity, Ordering ordering, Paging paging) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.activity = activity;
        this.ordering = ordering;
        this.paging = paging;
    }





    public Long getCustomerID() {
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

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchCustomerRequest that = (SearchCustomerRequest) o;
        return activity == that.activity &&
                Objects.equals(customerID, that.customerID) &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(customerEmail, that.customerEmail) &&
                Objects.equals(customerPhone, that.customerPhone) &&
                Objects.equals(ordering, that.ordering) &&
                Objects.equals(paging, that.paging);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, customerName, customerEmail, customerPhone, activity, ordering, paging);
    }
}
