package estore.core.domain;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name="productcategory")
public class ProductCategory {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="category")
    private String category;

    public ProductCategory(String category) {
        this.category = category;
    }

    public ProductCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "category='" + category + '\'' +
                '}';
    }
}
