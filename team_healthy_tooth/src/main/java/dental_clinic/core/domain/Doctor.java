package dental_clinic.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "isEmployed")
    private boolean isEmployed = true;

    public Doctor() { }

    public Doctor(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        isEmployed = true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEmployed() { return isEmployed; }

    public void setEmployed(boolean employed) {
        isEmployed = employed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return name.equalsIgnoreCase(doctor.name) && surname.equalsIgnoreCase(doctor.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        return "Doctor: " +
                "id: " + id + "\n" +
                "Dr: " + name + " " + surname + "\n" +
                "Phone: " + phone + " \n" +
                "Is employed: " + isEmployed + "\n\n";
    }
}
