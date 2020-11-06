import java.util.Objects;

public class Product {
    //private int id;
    private String name;
    private String description;
    private int quantity;
    private int price;

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
        this.quantity = 0;
        this.price = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, quantity);
    }

    @Override
    public String toString() {
        return "Product {" +
                "name : " + this.name +
                ", description : " + this.description +
                ", available : " + this.quantity +
                ", price per unit : " + this.price +
                '}';
    }
}
