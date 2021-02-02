package internet_store.core.service.product;


import internet_store.core.request.product.product_items.AddProductQuantityRequest;
import internet_store.core.response.product.product_item.AddProductQuantityResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddProductQuantityServiceTest {
    AddProductQuantityService service = new AddProductQuantityService();

    @Test
    public void shouldReturnCurrentInt() {
        AddProductQuantityRequest request = new AddProductQuantityRequest(55L);
        AddProductQuantityResponse response = service.execute(request);
        boolean result = response.hasErrors();
        assertFalse(result);
    }

    @Test
    public void shouldReturnError_1() {
        AddProductQuantityRequest request = new AddProductQuantityRequest(-1L);
        AddProductQuantityResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }
}