package internet_store.core.service.client;


import internet_store.core.request.client.client_items.AddClientEmailRequest;
import internet_store.core.response.client.client_items.AddClientEmailResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddClientEmailServiceTest {

    AddClientEmailService service = new AddClientEmailService();

    @Test
    public void shouldReturnCurrentString() {
        AddClientEmailRequest request = new AddClientEmailRequest("alex@alex.com");
        AddClientEmailResponse response = service.execute(request);
        assertEquals("alex@alex.com", response.getEmail());
    }

    @Test
    public void shouldReturnError_1() {
        AddClientEmailRequest request = new AddClientEmailRequest("alex_alex.com");
        AddClientEmailResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("email unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        AddClientEmailRequest request = new AddClientEmailRequest("");
        AddClientEmailResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("email unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_3() {
        AddClientEmailRequest request = new AddClientEmailRequest(null);
        AddClientEmailResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("email unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_4() {
        AddClientEmailRequest request = new AddClientEmailRequest("alex@alex");
        AddClientEmailResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("email unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_5() {
        AddClientEmailRequest request = new AddClientEmailRequest("no name");
        AddClientEmailResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("email unsupported format", response.getErrors().get(0).getMessage());
    }
}