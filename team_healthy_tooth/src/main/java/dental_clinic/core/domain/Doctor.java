package dental_clinic.core.domain;

import java.util.Objects;

public class Doctor {

    private Long id;
    private String name;
    private String surname;
    private boolean isEmployed = true;

    public Doctor(String name, String surname) {
        this.name = name;
        this.surname = surname;
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

    public String getSurname() {
        return surname;
    }

    public boolean getIsEmployed() {
        return isEmployed;
    }

    public void setEmployed(boolean employed) {
        isEmployed = employed;
    }

    public boolean filledNameAndSurname() {
        return name != null && !name.isEmpty() && surname != null && !surname.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(name, doctor.name) && Objects.equals(surname, doctor.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        return "Doctor: " +
                "id: " + id + "\n" +
                "name: '" + name + "\n" +
                "surname: '" + surname + "\n";
    }
}
