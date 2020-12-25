package team_VK.application.core.domain;

import java.util.Objects;

public class Client {

    public long clientID;
    public String clientFirstName;
    private String clientLastName;
    private String clientPersonalCode;

    public Client(String clientFirstName, String clientLastName, String clientPersonalCode) {

        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientPersonalCode = clientPersonalCode;
    }

    public long getClientID() {
        return clientID;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public String getClientPersonalCode() {
        return clientPersonalCode;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
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


