package internet_store.core.service.ordering;

import internet_store.core.request.ordering.CheckOrderIdRequest;
import internet_store.core.response.ordering.CheckOrderIdResponse;
import internet_store.database.order_database.InnerOrderDatabase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class CheckOrderServiceTest {
    @Mock
    private InnerOrderDatabase orderDatabase;
    @InjectMocks
    private CheckOrderService service;

    @Test
    public void shouldReturnNoErrors() {
        Mockito.when(orderDatabase.isIdExist(1L)).thenReturn(true);
        CheckOrderIdResponse response = service.execute(new CheckOrderIdRequest(1L));
        assertFalse(response.hasErrors());
        assertEquals(1L,response.getId());
    }

    @Test
    public void shouldReturnError_1() {
        CheckOrderIdResponse response = service.execute(new CheckOrderIdRequest(-1L));
        assertTrue(response.hasErrors());
        assertEquals("Long input error ", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        CheckOrderIdResponse response = service.execute(new CheckOrderIdRequest(25L));
        assertTrue(response.hasErrors());
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }
}