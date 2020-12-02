package team_VK.application.core.requests;

import java.util.Objects;

public class AddClientRequest {

    private String clientFirstName;
    private String clientLastName;
    private String clientPersonalCode;

    public AddClientRequest(String clientFirstName, String clientLastName, String clientPersonalCode) {
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientPersonalCode = clientPersonalCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddClientRequest that = (AddClientRequest) o;
        return Objects.equals(clientFirstName, that.clientFirstName) &&
                Objects.equals(clientLastName, that.clientLastName) &&
                Objects.equals(clientPersonalCode, that.clientPersonalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientFirstName, clientLastName, clientPersonalCode);
    }

    @Override
    public String toString() {
        return "AddClientRequest{" +
                "clientFirstName='" + clientFirstName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", clientPersonalCode='" + clientPersonalCode + '\'' +
                '}';
    }
}
