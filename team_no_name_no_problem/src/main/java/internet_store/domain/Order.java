package internet_store.domain;

public class Order {

    Customer customer;

    ShoppingCart shoppingCart;

    public Order (Customer customer, ShoppingCart shoppingCart){
        this.customer = customer;
        this.shoppingCart = shoppingCart;
    }



}
