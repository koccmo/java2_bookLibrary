package estore.domain;

import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private String description;
    private String category;
    private int quantity;
    private double price;

    public Product() {}

    public Product(String name, String description, String category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.quantity = 0;
        this.price = 0;
    }

    public Product(String name, String description, String category, int quantity, double price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category, quantity, price);
    }

    @Override
    public String toString() {
        return "Product {" + "id : " + this.id +
                ", name : " + this.name +
                ", description : " + this.description +
                ", category : " + this.category +
                ", available : " + this.quantity +
                ", price per unit : " + this.price +
                '}';
    }
}