package lv.estore.app.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="email", nullable = false)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "profile_created")
    private Date profileCreatedAt;

    public User(){
    }

    public User(String firstName, String lastName, String email, Date profileCreatedAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profileCreatedAt = profileCreatedAt;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getProfileCreatedAt() {
        return profileCreatedAt;
    }

    public void setProfileCreatedAt(Date profileCreatedAt) {
        this.profileCreatedAt = profileCreatedAt;
    }


    @Override
    public String toString() {
        return "User:" +
                "id = " + id + "; " +
                "firstName = " + firstName + "; " +
                "lastName = " + lastName + "; " +
                "email = " + email + "; " +
                "profileCreatedAt = " + profileCreatedAt + "; " ;
    }
}
