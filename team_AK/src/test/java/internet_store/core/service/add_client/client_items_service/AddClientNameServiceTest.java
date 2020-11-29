package internet_store.core.service.add_client.client_items_service;

import internet_store.core.request.client.client_items.AddClientNameRequest;
import internet_store.core.response.client.client_items.AddClientNameResponse;
import internet_store.core.service.client.AddClientNameService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddClientNameServiceTest {
    AddClientNameService service = new AddClientNameService();

    @Test
    public void shouldReturnCurrentString() {
        AddClientNameRequest request = new AddClientNameRequest("Name");
        AddClientNameResponse response = service.execute(request);
        assertEquals("Name", response.getName());
    }

    @Test
    public void shouldReturnError_1() {
        AddClientNameRequest request = new AddClientNameRequest("");
        AddClientNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        AddClientNameRequest request = new AddClientNameRequest(null);
        AddClientNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }
}