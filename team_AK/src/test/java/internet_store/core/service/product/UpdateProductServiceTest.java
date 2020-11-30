package internet_store.core.service.product;

import internet_store.core.request.product.UpdateProductRequest;
import internet_store.core.response.product.UpdateProductResponse;
import internet_store.database.product_database.InnerProductDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateProductServiceTest {
    @Mock
    private InnerProductDatabase productDatabase;
    @InjectMocks
    private UpdateProductService service;

    @Test
    public void shouldReturnNoIdError() {
        Mockito.when(productDatabase.isIdExist(1L)).thenReturn(false);
        UpdateProductResponse response = service.execute(new UpdateProductRequest(1L));

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorNegativeNumber() {
        Mockito.when(productDatabase.isIdExist(-15L)).thenReturn(true);
        UpdateProductResponse response = service.execute(new UpdateProductRequest(-15L));

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Long input error ", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorNegativeNumberAndNoId() {
        Mockito.when(productDatabase.isIdExist(-15L)).thenReturn(false);
        UpdateProductResponse response = service.execute(new UpdateProductRequest(-15L));

        assertTrue(response.hasErrors());
        assertEquals(2, response.getErrors().size());
        assertEquals("Long input error ", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
        assertEquals("Id error ", response.getErrors().get(1).getField());
        assertEquals("wrong ID", response.getErrors().get(1).getMessage());
    }

    @Test
    public void shouldReturnNoError() {
        Mockito.when(productDatabase.isIdExist(1L)).thenReturn(true);
        UpdateProductResponse response = service.execute(new UpdateProductRequest(1L));

        assertFalse(response.hasErrors());
        assertEquals(1L, response.getId());
    }
}