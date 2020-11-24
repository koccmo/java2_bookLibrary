package internet_store.application.core.domain;

//import lombok.Data;

import java.util.Objects;

//@Data
public class Client {
    private Long clientId;
    private String clientFirstName;
    private String clientSecondName;
    private String clientPhone;
    private String clientEmail;

    public Client(Long clientId, String clientFirstName, String clientSecondName, String clientPhone, String clientEmail) {
        this.clientId = clientId;
        this.clientFirstName = clientFirstName;
        this.clientSecondName = clientSecondName;
        this.clientPhone = clientPhone;
        this.clientEmail = clientEmail;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientSecondName() {
        return clientSecondName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public void setClientSecondName(String clientSecondName) {
        this.clientSecondName = clientSecondName;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(clientId, client.clientId) &&
                Objects.equals(clientFirstName, client.clientFirstName) &&
                Objects.equals(clientSecondName, client.clientSecondName) &&
                Objects.equals(clientPhone, client.clientPhone) &&
                Objects.equals(clientEmail, client.clientEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientFirstName, clientSecondName, clientPhone, clientEmail);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientFirstName='" + clientFirstName + '\'' +
                ", clientSecondName='" + clientSecondName + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                '}';
    }

}

