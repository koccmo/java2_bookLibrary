package dental_clinic.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="manipulation")
public class Manipulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="manipulation_type", nullable = false)
    private String manipulationType;

    @Column(name="price", nullable = false)
    private Integer price;

    @Column(name="isActive", nullable = false)
    private boolean isActive = true;

    public Manipulation() { }

    public Manipulation(String manipulationType, Integer price) {
        this.manipulationType = manipulationType;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManipulationType() {
        return manipulationType;
    }

    public void setManipulationType(String manipulationType) {
        this.manipulationType = manipulationType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manipulation that = (Manipulation) o;
        return isActive == that.isActive && Objects.equals(manipulationType, that.manipulationType) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manipulationType, price, isActive);
    }

    @Override
    public String toString() {
        return "Manipulation:\n" +
                "id: " + id +
                ", manipulation_type: " + manipulationType +
                ", price: " + price +
                ", isActive: " + isActive;
    }
}
