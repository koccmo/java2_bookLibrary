package electronic_library.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "readers")
public class Reader {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="firstName", nullable = false)
    private String readerFirstName;

    @Column(name="lastName", nullable = false)
    private String readerLastName;

    @Column(name="personalCode", nullable = false)
    private String readerPersonalCode;

    @Column(name="phoneNumber", nullable = false)
    private String readerPhoneNumber;

    @Column(name="email", nullable = false)
    private String readerEmail;

    @Column(name="address")
    private String readerAddress;

    public Reader(){
    }

    public Reader(String readerFirstName, String readerLastName, String readerPersonalCode) {
        this.readerFirstName = readerFirstName;
        this.readerLastName = readerLastName;
        this.readerPersonalCode = readerPersonalCode;
    }

    public Reader(String readerFirstName, String readerLastName, String readerPersonalCode, String readerPhoneNumber) {
        this.readerFirstName = readerFirstName;
        this.readerLastName = readerLastName;
        this.readerPersonalCode = readerPersonalCode;
        this.readerPhoneNumber = readerPhoneNumber;
    }

    public Reader(String readerFirstName, String readerLastName, String readerPersonalCode, String readerPhoneNumber, String readerEmail) {
        this.readerFirstName = readerFirstName;
        this.readerLastName = readerLastName;
        this.readerPersonalCode = readerPersonalCode;
        this.readerPhoneNumber = readerPhoneNumber;
        this.readerEmail = readerEmail;
    }

    public Reader(String readerFirstName, String readerLastName, String readerPersonalCode, String readerPhoneNumber, String readerEmail, String readerAddress) {
        this.readerFirstName = readerFirstName;
        this.readerLastName = readerLastName;
        this.readerPersonalCode = readerPersonalCode;
        this.readerPhoneNumber = readerPhoneNumber;
        this.readerEmail = readerEmail;
        this.readerAddress = readerAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReaderFirstName() {
        return readerFirstName;
    }

    public void setReaderFirstName(String readerFirstName) {
        this.readerFirstName = readerFirstName;
    }

    public String getReaderLastName() {
        return readerLastName;
    }

    public void setReaderLastName(String readerLastName) {
        this.readerLastName = readerLastName;
    }

    public String getReaderPersonalCode() {
        return readerPersonalCode;
    }

    public void setReaderPersonalCode(String readerPersonalCode) {
        this.readerPersonalCode = readerPersonalCode;
    }

    public String getReaderPhoneNumber() {
        return readerPhoneNumber;
    }

    public void setReaderPhoneNumber(String readerPhoneNumber) {
        this.readerPhoneNumber = readerPhoneNumber;
    }

    public String getReaderEmail() {
        return this.readerEmail;
    }

    public void setReaderEmail(String readerEmail) {
        this.readerEmail = readerEmail;
    }

    public String getReaderAddress() {
        return readerAddress;
    }

    public void setReaderAddress(String readerAddress) {
        this.readerAddress = readerAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return Objects.equals(id, reader.id) &&
               Objects.equals(readerFirstName, reader.readerFirstName) &&
               Objects.equals(readerLastName, reader.readerLastName) &&
               Objects.equals(readerPersonalCode, reader.readerPersonalCode) &&
               Objects.equals(readerPhoneNumber, reader.readerPhoneNumber) &&
               Objects.equals(readerEmail, reader.readerEmail) &&
               Objects.equals(readerAddress, reader.readerAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, readerFirstName, readerLastName, readerPersonalCode, readerPhoneNumber, readerEmail, readerAddress);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", readerFirstName='" + readerFirstName + '\'' +
                ", readerLastName='" + readerLastName + '\'' +
                ", readerPersonalCode='" + readerPersonalCode + '\'' +
                ", readerPhoneNumber='" + readerPhoneNumber + '\'' +
                ", readerEmail='" + readerEmail + '\'' +
                ", readerAddress='" + readerAddress + '\'' +
                '}';
    }
}
