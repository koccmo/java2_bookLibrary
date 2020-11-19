package lesson_3.core.service.add_product.product_items_service;


import lesson_3.core.request.add_product.product_items.AddProductQuantityRequest;
import lesson_3.core.response.add_product.product_items.AddProductQuantityResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddProductQuantityServiceTest {
    AddProductQuantityService service = new AddProductQuantityService();

    @Test
    public void shouldReturnCurrentInt() {
        AddProductQuantityRequest request = new AddProductQuantityRequest(55);
        AddProductQuantityResponse response = service.execute(request);
        assertEquals(55, response.getQuantity());
    }

    @Test
    public void shouldReturnError_1() {
        AddProductQuantityRequest request = new AddProductQuantityRequest(-1);
        AddProductQuantityResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }
}