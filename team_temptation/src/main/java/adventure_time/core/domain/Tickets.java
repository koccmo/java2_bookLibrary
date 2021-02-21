package adventure_time.core.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table (name = "tickets")
public class Tickets {

    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "number", unique = true, nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn (name = "tour", nullable = false)
    private Tours tour;

    @ManyToOne
    @JoinColumn (name = "customer", nullable = false)
    private Customers customer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "issue_date", nullable = false)
    private Date issueDate;

    @Column (name = "validity")
    private Boolean validity;

    public Tickets() {}

    public Tickets(String number, Tours tour, Customers customer) {
        this.number = number;
        this.tour = tour;
        this.customer = customer;
        this.issueDate = new Date();
        this.validity = true;
    }

    public Boolean isValid () { return this.validity;}

    /*------------------------------------------------------*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Tours getTour() {
        return tour;
    }

    public void setTour(Tours tour) {
        this.tour = tour;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Boolean getValidity() {
        return validity;
    }

    public void setValidity(Boolean validity) {
        this.validity = validity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tickets tickets = (Tickets) o;
        return Objects.equals(id, tickets.id) &&
                Objects.equals(number, tickets.number) &&
                Objects.equals(tour, tickets.tour) &&
                Objects.equals(customer, tickets.customer) &&
                Objects.equals(issueDate, tickets.issueDate) &&
                Objects.equals(validity, tickets.validity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, tour, customer, issueDate, validity);
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", tour=" + tour +
                ", customer=" + customer +
                ", issueDate=" + issueDate +
                ", validity=" + validity +
                '}';
    }
}
