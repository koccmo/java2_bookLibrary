package internet_store.core.domain;

public class Order {

    private Long id;

    private Customer customer;

    private ShoppingCart shoppingCart;

    public Order (Customer customer, ShoppingCart shoppingCart){
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

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }


}
