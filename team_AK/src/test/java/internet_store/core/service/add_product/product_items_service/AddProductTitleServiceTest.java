package internet_store.core.service.add_product.product_items_service;

import internet_store.core.request.product.product_items.AddProductTitleRequest;
import internet_store.core.response.product.product_item.AddProductTitleResponse;
import internet_store.core.service.product.AddProductTitleService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddProductTitleServiceTest {
    AddProductTitleService service = new AddProductTitleService();

    @Test
    public void shouldReturnCurrentString() {
        AddProductTitleRequest request = new AddProductTitleRequest("item");
        AddProductTitleResponse response = service.execute(request);
        assertEquals("item", response.getProductTitle());
    }

    @Test
    public void shouldReturnError_1() {
        AddProductTitleRequest request = new AddProductTitleRequest("");
        AddProductTitleResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        AddProductTitleRequest request = new AddProductTitleRequest(null);
        AddProductTitleResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }
}