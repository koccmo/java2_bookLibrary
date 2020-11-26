package internet_store.core.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Long id;
    private String title;
    private String description;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal sum;

    public Product() {
    }

    public Product(Product other) {
        this.id = other.id;
        this.title = other.title;
        this.description = other.description;
        this.quantity = other.quantity;
        this.price = other.price;
        this.sum = other.sum;
    }
}