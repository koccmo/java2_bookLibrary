package online_schedule.core.domain;

import java.util.Objects;

public class PersonalData {

    long id;
    private String name ;
    private String surname;
    private String mobileNumber;

    public PersonalData(String name, String surname, String mobileNumber){
        this.name = name;
        this.surname = surname;
        this.mobileNumber = mobileNumber;

    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PersonalData personalData = (PersonalData) obj;
        return Objects.equals(mobileNumber, personalData.mobileNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobileNumber);
    }

    @Override
    public String toString() {
        return "\nPersonal data:{" +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + mobileNumber + '\'' +
                "\n";
    }

    public void setId(Long id) {
    }

    public long getId() {
        return id;
    }
}
