package estore.core.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="products")
public class Product {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="prodName")
    private String name;

    @Column(name="prodDescription")
    private String description;

    @ManyToOne
    @JoinColumn(name="category_id")
    private ProductCategory category;

    @Column(name="quantity")
    private int quantity;

    @Column(name="price")
    private double price;

    @CreatedDate
    @Column(name="dateOnStock")
    private LocalDateTime dateOnStock = LocalDateTime.now();

    public Product() {}

    public Product(String name, String description, ProductCategory category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.quantity = 0;
        this.price = 0;
    }

    public Product(String name, String description, ProductCategory category, int quantity, double price) {
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

//    public Long getCategory() {
//        return category;
//    }
//
//    public void setCategory(Long category) {
//        this.category = category;
//    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public LocalDateTime getDateOnStock() {
        return dateOnStock;
    }

    public void setDateOnStock(LocalDateTime dateOnStock) {
        this.dateOnStock = dateOnStock;
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
                ", category : " + this.category.getCategory() +
                ", available : " + this.quantity +
                ", price per unit : " + this.price +
                '}';
    }
}