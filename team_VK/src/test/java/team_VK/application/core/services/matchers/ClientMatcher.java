package team_VK.application.core.services.matchers;

import org.mockito.ArgumentMatcher;
import team_VK.application.core.domain.Client;

public class ClientMatcher implements ArgumentMatcher<Client> {

    public String clientFirstName;
    private String clientLastName;
    private String clientPersonalCode;

    public ClientMatcher(String clientFirstName, String clientLastName, String clientPersonalCode) {
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientPersonalCode = clientPersonalCode;
    }

    @Override
    public boolean matches(Client client) {
        return client.getClientFirstName().equals(clientFirstName)
                && client.getClientLastName().equals(clientLastName)
                && client.getClientPersonalCode().equals(clientPersonalCode);
    }
}
