package internet_store.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @Column(name = "shopping_cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "sum_total")
    private Integer sumTotal;

    public ShoppingCart(Customer customer, Integer sumTotal) {
        this.customer = customer;
        this.sumTotal = sumTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Integer getSumTotal()
    {return sumTotal;}

    public void setSumTotal(Integer sumTotal) {
        this.sumTotal = sumTotal;
    }

    public ShoppingCart () { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(id, that.id) && Objects.equals(customer, that.customer) && Objects.equals(sumTotal, that.sumTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, sumTotal);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", customer=" + customer +
                ", sumTotal=" + sumTotal +
                '}';
    }
}
