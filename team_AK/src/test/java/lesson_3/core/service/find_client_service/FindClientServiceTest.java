package lesson_3.core.service.find_client_service;

import lesson_3.core.domain.Client;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FindClientServiceTest {
    private static final List<Client> clients = new ArrayList<>();
    FindClientService findService = new FindClientService();

    @BeforeClass
    public static void setup() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("29556699");
        client.setEmail("a@a.lv");
        clients.add(client);

        Client client_1 = new Client();
        client_1.setId(2L);
        client_1.setName("Name_1");
        client_1.setSurname("Surname_1");
        client_1.setPhoneNumber("29333333");
        client_1.setEmail("a1@a1.lv");
        clients.add(client_1);

        Client client_2 = new Client();
        client_2.setId(3L);
        client_2.setName("Name_2");
        client_2.setSurname("Surname_2");
        client_2.setPhoneNumber("29444444");
        client_2.setEmail("a2@a2.lv");
        clients.add(client_2);
    }

    @Test
    public void checkId_1() {
        boolean result = findService.isIdExist(clients, 1L);
        assertTrue(result);
    }

    @Test
    public void checkId_2() {
        boolean result = findService.isIdExist(clients, 3L);
        assertTrue(result);
    }

    @Test
    public void mustReturnFalse_IdNoExist() {
        boolean result = findService.isIdExist(clients, 10L);
        assertFalse(result);
    }

    @Test
    public void findClientById_1() {
        Client client = findService.findById(clients, 1);
        long id = client.getId();
        assertEquals(1L, id);
        assertEquals("Name", client.getName());
    }

    @Test
    public void findClientById_MustReturnNull() {
        Client client = findService.findById(clients, 5);
        assertNull(client);
    }

    @Test
    public void findClientIndex_1() {
        int result = findService.findClientIndex(clients, 1);
        assertEquals(0, result);
    }

    @Test
    public void findClientIndex_MustReturnError() {
        int result = findService.findClientIndex(clients, 15);
        assertEquals(-1, result);
    }

    @Test
    public void checkClientPhone() {
        boolean result = findService.isClientPhoneNumber(clients, "29444444");
        assertTrue(result);
    }

    @Test
    public void checkClientPhone_MustReturnError() {
        boolean result = findService.isClientPhoneNumber(clients, "26778899");
        assertFalse(result);
    }
}