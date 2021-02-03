//package internet_store.core.service.ordering;
//
//import internet_store.core.domain.Client;
//import internet_store.core.domain.Order;
//import internet_store.core.request.ordering.FindByOrderNumberRequest;
//import internet_store.database.order_database.InnerOrderDatabase;
//import internet_store.database.order_database.InnerOrderDatabaseImpl;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//
//@Ignore
//public class FindByOrderNumberServiceTest {
//
//    private final InnerOrderDatabase orderDatabase = new InnerOrderDatabaseImpl();
//    private final FindByOrderNumberService service = new FindByOrderNumberService(orderDatabase);
//
//    @Test
//    public void shouldReturnOrder() {
//        Client client = new Client();
//        Order order = new Order(client);
//        orderDatabase.addOrder(order);
//        Order result = service.execute(new FindByOrderNumberRequest(100));
//        assertEquals(order, result);
//    }
//
//    @Test
//    public void shouldReturnNull() {
//        Client client = new Client();
//        Order order = new Order(client);
//        orderDatabase.addOrder(order);
//        Order result = service.execute(new FindByOrderNumberRequest(1));
//        assertNull(result);
//    }
//}