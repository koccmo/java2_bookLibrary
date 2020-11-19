package team_VK.application.core.domain;

import java.util.Objects;

public class Client {

    public long clientID;
    public String clientName;


    public Client(String clientName) {
        this.clientName = clientName;
    }

    public long getClientID() {
        return clientID;
    }

    public String getClientName() {
        return clientName;
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
                Objects.equals(clientName, client.clientName) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientID, clientName);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='"  + '\'' +
                ", clientCode='" +  '\'' +
                '}';
    }
}


