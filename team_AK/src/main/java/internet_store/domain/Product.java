package internet_store.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Long id;
    private String title;
    private String description;
    private Integer quantity;
    private BigDecimal price;
}