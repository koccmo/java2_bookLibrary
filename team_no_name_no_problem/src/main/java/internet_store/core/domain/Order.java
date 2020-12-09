package internet_store.core.domain;

import java.util.Map;
import java.util.Objects;

public class Order {

    private Long id;

    private Customer customer;

    private Map<Product, Integer> shoppingCart;

    public Order (Customer customer, Map <Product, Integer> shoppingCart){
        this.customer = customer;
        this.shoppingCart = shoppingCart;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(customer, order.customer) && Objects.equals(shoppingCart, order.shoppingCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, shoppingCart);
    }

    @Override
    public String toString() {
        return  "Order: {" +
                "\ncustomer=" + customer +
                "\nshoppingCart=" + shoppingCart +
                "}";
    }
}
