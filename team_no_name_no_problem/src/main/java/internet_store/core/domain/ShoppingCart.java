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

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "sum_total")
    private Integer sumTotal;

    public Integer getSumTotal() {
         Integer sum = 0;
      return getSumTotal();
    }

   /* public Integer getSumTotal() {
        Integer sum = 0;
        for (Product product : shoppingCart.keySet()) {
            sum += shoppingCart.get(product) * product.getPrice();
        }
        return sum;
    }*/

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
