package lesson_8.mysql_spring.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

    @Override
    public String toString() {
        return "id=" + this.getId() + " name=" + this.getName() + " surname=" + this.getSurname()
                + " phone=" + this.getPhoneNumber() + " email=" + this.getEmail();
    }
}