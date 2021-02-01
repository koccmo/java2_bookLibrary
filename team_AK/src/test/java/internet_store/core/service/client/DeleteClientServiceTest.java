package internet_store.core.service.client;

import internet_store.core.domain.Client;
import internet_store.core.request.client.DeleteClientRequest;
import internet_store.core.response.client.DeleteClientResponse;
import internet_store.database.interfaces.ClientDatabase;
import internet_store.database.client_database.ClientDatabaseImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DeleteClientServiceTest {
    private final ClientDatabase clientDatabase = new ClientDatabaseImpl();
    private final DeleteClientService deleteService = new DeleteClientService();

    @Test
    public void shouldReturnNoErrors() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("29336699");
        client.setEmail("a@a.lv");
        clientDatabase.addClient(client);

        DeleteClientResponse response = deleteService.execute(new DeleteClientRequest(1L, clientDatabase, new Client()));
        assertEquals(1, response.getId());
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnError_1() {
        DeleteClientResponse response = deleteService.execute(new DeleteClientRequest(-1L, clientDatabase, new Client()));
        assertEquals("Long input error ", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        DeleteClientResponse response = deleteService.execute(new DeleteClientRequest(25L, clientDatabase, new Client()));
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }
}