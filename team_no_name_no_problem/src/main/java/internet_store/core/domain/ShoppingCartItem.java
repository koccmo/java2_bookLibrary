package internet_store.core.domain;

import java.util.Objects;

public class ShoppingCartItem {

    private ShoppingCart shoppingCart;

    private Product product;

    private Integer quantity;

    private int price;

    public ShoppingCartItem (){ }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItem that = (ShoppingCartItem) o;
        return price == that.price && Objects.equals(shoppingCart, that.shoppingCart) && Objects.equals(product, that.product) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingCart, product, quantity, price);
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "shoppingCart=" + shoppingCart +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
