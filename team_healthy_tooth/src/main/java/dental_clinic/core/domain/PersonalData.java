package dental_clinic.core.domain;

import java.util.Objects;

public class PersonalData {

    private Long id;
    private final String name;
    private String surname;
    private String phone;
    private final String personalCode;


    public PersonalData(String name, String surname, String phone, String personalCode){
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.personalCode = personalCode;

    }

    public Long getId (){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setId (Long id){
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalData personalData = (PersonalData) o;
        return Objects.equals(personalCode, personalData.personalCode);

    }

    @Override
    public int hashCode() {
        return Objects.hash(personalCode);
    }

    @Override
    public String toString() {
        return "\nPersonal data:{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", personalCode='" + personalCode + "\n";
    }
}
