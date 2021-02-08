package team_VK.application.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="clients")

public class Client {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long clientID;

    @Column(name="first_name", nullable = false)
    public String clientFirstName;
    @Column(name="last_name", nullable = false)
    private String clientLastName;
    @Column(name="personalCode", nullable = false)
    private String clientPersonalCode;

    public Client(String clientFirstName, String clientLastName, String clientPersonalCode) {

        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientPersonalCode = clientPersonalCode;
    }

    public Client() {}

    public long getClientID() {
        return clientID;
    }
    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }
    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }
    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientPersonalCode() {
        return clientPersonalCode;
    }
    public void setClientPersonalCode(String clientPersonalCode) {
        this.clientPersonalCode = clientPersonalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return clientID == client.clientID &&
                Objects.equals(clientFirstName, client.clientFirstName) &&
                Objects.equals(clientLastName, client.clientLastName) &&
                Objects.equals(clientPersonalCode, client.clientPersonalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientID, clientFirstName, clientLastName, clientPersonalCode);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", clientFirstName='" + clientFirstName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", clientPersonalCode='" + clientPersonalCode + '\'' +
                '}';
    }
}


