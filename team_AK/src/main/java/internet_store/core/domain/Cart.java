package internet_store.core.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "cart", schema = "store")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "product_title")
    String title;
    @Column(name = "quantity")
    Long quantity;
    @Column(name = "price")
    BigDecimal price;
    @Column(name = "sum")
    BigDecimal sum;
    @Column(name = "deleted")
    Boolean deleted = false;
}