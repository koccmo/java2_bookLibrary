package internet_store.core.service.add_client.client_items_service;

import internet_store.core.request.client.client_items.AddClientPhoneRequest;
import internet_store.core.response.client.client_items.AddClientPhoneResponse;
import internet_store.core.service.client.AddClientPhoneService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddClientPhoneServiceTest {
    AddClientPhoneService service = new AddClientPhoneService();

    @Test
    public void shouldReturnCurrentString_1() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("+37122556688");
        AddClientPhoneResponse response = service.execute(request);
        assertEquals("+37122556688", response.getPhoneNumber());
    }

    @Test
    public void shouldReturnCurrentString_2() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("29546321");
        AddClientPhoneResponse response = service.execute(request);
        assertEquals("29546321", response.getPhoneNumber());
    }

    @Test
    public void shouldReturnError_1() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("1234567");
        AddClientPhoneResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("phone number unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("1");
        AddClientPhoneResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("phone number unsupported format", response.getErrors().get(1).getMessage());
    }

    @Test
    public void shouldReturnError_3() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("no phone number");
        AddClientPhoneResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("phone number unsupported format", response.getErrors().get(1).getMessage());
    }
}