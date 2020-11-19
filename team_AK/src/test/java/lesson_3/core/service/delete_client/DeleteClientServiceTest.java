package lesson_3.core.service.delete_client;

import lesson_3.ProductListApplication;
import lesson_3.core.domain.Client;
import lesson_3.core.request.delete_client.DeleteClientRequest;
import lesson_3.core.response.delete_client.DeleteClientResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeleteClientServiceTest {
    DeleteClientService deleteService = new DeleteClientService(ProductListApplication.clientDatabase);

    @Test
    public void shouldReturnNoErrors() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("29336699");
        client.setEmail("a@a.lv");
        ProductListApplication.clientDatabase.addClient(client);

        DeleteClientResponse response = deleteService.execute(new DeleteClientRequest(1L));
        assertEquals(1, response.getId());
    }

    @Test
    public void shouldReturnError_1() {
        DeleteClientResponse response = deleteService.execute(new DeleteClientRequest(-1L));
        assertEquals("Long input error ", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        DeleteClientResponse response = deleteService.execute(new DeleteClientRequest(25L));
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }
}