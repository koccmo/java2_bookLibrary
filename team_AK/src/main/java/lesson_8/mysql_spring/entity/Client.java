package lesson_8.mysql_spring.entity;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
@Table(name = "client", schema = "store")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phone")
    private String phoneNumber;
    @Column(name = "email")
    private String email;

    @Override
    public String toString() {
        return "id=" + this.getId() + " name=" + this.getName() + " surname=" + this.getSurname()
                + " phone=" + this.getPhoneNumber() + " email=" + this.getEmail();
    }
}