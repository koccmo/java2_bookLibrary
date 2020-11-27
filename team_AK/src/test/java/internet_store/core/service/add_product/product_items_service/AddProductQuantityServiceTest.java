package internet_store.core.service.add_product.product_items_service;


import internet_store.core.request.add_product.product_items.AddProductQuantityRequest;
import internet_store.core.response.add_product.product_items.AddProductQuantityResponse;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddProductQuantityServiceTest {
    AddProductQuantityService service = new AddProductQuantityService();

    @Test
    public void shouldReturnCurrentInt() {
        AddProductQuantityRequest request = new AddProductQuantityRequest(new BigDecimal("55"));
        AddProductQuantityResponse response = service.execute(request);
        assertEquals(new BigDecimal("55"), response.getQuantity());
    }

    @Test
    public void shouldReturnError_1() {
        AddProductQuantityRequest request = new AddProductQuantityRequest(new BigDecimal("-1"));
        AddProductQuantityResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }
}