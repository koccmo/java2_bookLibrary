package lv.estore.app.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Table(name="products")
public class Product {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="price", nullable = false)
    private BigDecimal price;

    public Product(){
    }

    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return this.id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.getId())
                && Objects.equals(name, product.getName())
                && Objects.equals(description, product.getDescription())
                && Objects.equals(price, product.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, price);
    }

    public static Comparator<Product> idComparator = (p1, p2) -> {
        long productId1 = p1.getId();
        long productId2 = p2.getId();
        return (int)(productId1 - productId2);
    };

    @Override
    public String toString() {
        return "Product: " +
                "id = " + id + "; " +
                "name = " + name + "; " +
                "description = " + description + "; " +
                "price = " + price + "Eur";
    }
}
