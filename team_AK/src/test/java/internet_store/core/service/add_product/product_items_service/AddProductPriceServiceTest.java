package internet_store.core.service.add_product.product_items_service;


import internet_store.core.request.add_product.product_items.AddProductPriceRequest;
import internet_store.core.response.add_product.product_items.AddProductPriceResponse;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddProductPriceServiceTest {
    AddProductPriceService service = new AddProductPriceService();

    @Test
    public void shouldReturnCurrentInt() {
        AddProductPriceRequest request = new AddProductPriceRequest(new BigDecimal("105"));
        AddProductPriceResponse response = service.execute(request);
        assertEquals(new BigDecimal("105"), response.getPrice());
    }

    @Test
    public void shouldReturnError_1() {
        AddProductPriceRequest request = new AddProductPriceRequest(new BigDecimal("-10"));
        AddProductPriceResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }
}