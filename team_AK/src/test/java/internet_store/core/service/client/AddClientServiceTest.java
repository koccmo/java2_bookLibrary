package internet_store.core.service.client;

import internet_store.core.domain.Client;
import internet_store.core.request.client.AddClientRequest;
import internet_store.core.response.client.AddClientResponse;
import internet_store.database.client_database.ClientDatabaseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AddClientServiceTest {
    @Mock
    private ClientDatabaseImpl clientDatabase;
    @InjectMocks
    private AddClientService service;

    @Test
    public void shouldReturnNoError() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("29789123");
        client.setEmail("test@test.lv");
        AddClientResponse response = service.execute(new AddClientRequest(client, clientDatabase));

        assertFalse(response.hasErrors());
        Mockito.verify(clientDatabase).addClient(client);
    }

    @Test
    public void shouldReturnErrorNoName() {
        Client client = new Client();
        client.setId(1L);
        client.setName("");
        client.setSurname("Surname");
        client.setPhoneNumber("29789123");
        client.setEmail("test@test.lv");
        AddClientResponse response = service.execute(new AddClientRequest(client, clientDatabase));

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Name input error: ", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
        Mockito.verify(clientDatabase,Mockito.times(0)).addClient(client);
    }

    @Test
    public void shouldReturnErrorNoSurname() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Name");
        client.setSurname("");
        client.setPhoneNumber("29789123");
        client.setEmail("test@test.lv");
        AddClientResponse response = service.execute(new AddClientRequest(client, clientDatabase));

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Surname input error: ", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
        Mockito.verify(clientDatabase,Mockito.times(0)).addClient(client);
    }

    @Test
    public void shouldReturnErrorUnsupportedPhoneNumber() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("2978912");
        client.setEmail("test@test.lv");
        AddClientResponse response = service.execute(new AddClientRequest(client, clientDatabase));

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Phone number input error: ", response.getErrors().get(0).getField());
        assertEquals("Phone number unsupported format", response.getErrors().get(0).getMessage());
        Mockito.verify(clientDatabase,Mockito.times(0)).addClient(client);
    }

    @Test
    public void shouldReturnErrorDuplicatePhoneNumber() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("29789123");
        client.setEmail("test@test.lv");
        Mockito.when(clientDatabase.isClientPhoneNumber("29789123")).thenReturn(true);
        AddClientResponse response = service.execute(new AddClientRequest(client, clientDatabase));

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Phone number input error: ", response.getErrors().get(0).getField());
        assertEquals("Duplicate", response.getErrors().get(0).getMessage());
        Mockito.verify(clientDatabase,Mockito.times(0)).addClient(client);
    }

    @Test
    public void shouldReturnErrorUnsupportedEmailFormat() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("29789123");
        client.setEmail("test@testlv");
        AddClientResponse response = service.execute(new AddClientRequest(client, clientDatabase));

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Email input error: ", response.getErrors().get(0).getField());
        assertEquals("Email unsupported format", response.getErrors().get(0).getMessage());
        Mockito.verify(clientDatabase,Mockito.times(0)).addClient(client);
    }
}