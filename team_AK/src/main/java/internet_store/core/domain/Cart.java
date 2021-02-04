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
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    @Column(name = "quantity")
    Long quantity;
    @Column(name = "sum")
    BigDecimal sum;
    @Column(name = "deleted")
    Boolean deleted = false;
    @Column(name = "ordered")
    Boolean ordered = false;
}