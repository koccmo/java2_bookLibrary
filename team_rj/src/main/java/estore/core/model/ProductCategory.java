package estore.core.model;

import javax.persistence.*;

@Entity
@Table(name="productCategory")
public class ProductCategory {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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