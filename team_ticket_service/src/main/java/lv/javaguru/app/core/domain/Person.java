package lv.javaguru.app.core.domain;

import java.util.Objects;

public class Person {
    private String name;
    private String surname;
    private boolean isRegistered;
    private Long id = 1L;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = id++;
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;

    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        return "" + name + " " + surname;
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
}
