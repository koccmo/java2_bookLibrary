package internet_store.core.service.delete_order;

import internet_store.core.domain.Client;
import internet_store.core.domain.Order;
import internet_store.core.request.ordering.DeleteOrderRequest;
import internet_store.core.response.ordering.DeleteOrderResponse;
import internet_store.core.service.ordering.DeleteOrderService;
import internet_store.database.order_database.InnerOrderDatabase;
import internet_store.database.order_database.InnerOrderDatabaseImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeleteOrderServiceTest {
    private final InnerOrderDatabase orderDatabase = new InnerOrderDatabaseImpl();
    DeleteOrderService orderService = new DeleteOrderService(orderDatabase);

    @Test
    public void shouldReturnNoErrors() {
        Order order = new Order(new Client());
        order.setId(1L);
        orderDatabase.addOrder(order);

        DeleteOrderResponse response = orderService.execute(new DeleteOrderRequest(1L));
        assertEquals(1L, response.getId());
    }

    @Test
    public void shouldReturnError_1() {
        DeleteOrderResponse response = orderService.execute(new DeleteOrderRequest(-1L));
        assertEquals("Long input error ", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        DeleteOrderResponse response = orderService.execute(new DeleteOrderRequest(5L));
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_3() {
        DeleteOrderResponse response = orderService.execute(new DeleteOrderRequest(0L));
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }
}