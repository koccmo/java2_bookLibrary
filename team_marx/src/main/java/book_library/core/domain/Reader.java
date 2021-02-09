package book_library.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "readers")
public class Reader {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "personal_code")
    private Long personalCode;

    public Reader() {
    }

    public Reader(String firstName, String lastName, Long personalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalCode = personalCode;
    }

    public Reader(Long id, String firstName, String lastName, Long personalCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalCode = personalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(Long personalCode) {
        this.personalCode = personalCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, personalCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return firstName.equals(reader.firstName) &&
                lastName.equals(reader.lastName) &&
                personalCode.equals(reader.personalCode);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", first name='" + firstName + '\'' +
                ", last name='" + lastName + '\'' +
                ", personal code='" + personalCode + '\'' +
                '}';
    }
}

