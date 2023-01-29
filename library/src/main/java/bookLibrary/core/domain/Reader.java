package bookLibrary.core.domain;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "readers")
public class Reader {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "personal_code", nullable = false)
    private Long personal_code;
    public  Reader() {}

    public Reader(String firstName, String lastName, Long personal_code) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personal_code = personal_code;
    }

    public Long getPersonal_code() {
        return personal_code;
    }

    public void setPersonal_code(Long personal_code) {
        this.personal_code = personal_code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return Objects.equals(id, reader.id) && Objects.equals(firstName, reader.firstName) && Objects.equals(lastName, reader.lastName) && Objects.equals(personal_code, reader.personal_code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, personal_code);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personal_code=" + personal_code +
                '}';
    }
}
