package internet_store.core.domain;

import java.util.Map;
import java.util.Objects;

public class Order {

    private Long id;

    private Customer customer;

    private Map<Product, Integer> shoppingCart;

    private Integer sumTotal;

    public Order (Customer customer, Map <Product, Integer> shoppingCart, Integer sumTotal){
        this.customer = customer;
        this.shoppingCart = shoppingCart;
        this.sumTotal = sumTotal;
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

    public Integer getSumTotal() {
        Integer sum = 0;
        for (Product product : shoppingCart.keySet()) {
            sum += shoppingCart.get(product) * product.getPrice();
        }
        return sum;
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
        return "Order details:" +
                customer + "\n" +
                "product list:" +
                shoppingCartToStringForPrint(shoppingCart);
    }

    private String shoppingCartToStringForPrint (Map<Product, Integer> shoppingCart) {
        String result = "";
        for (Product product : shoppingCart.keySet()) {
            result += product + " " + shoppingCart.get(product) + "\n";
        }
        result += "Sum = " + sumTotal + " EUR\n";
        return result;
    }
}
