package internet_store.core.service.ordering;

import internet_store.core.domain.Order;
import internet_store.core.operation.Tax;
import internet_store.core.service.cart.TotalSumCartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @Mock
    TotalSumCartService totalSumCartService;
    @Mock
    CreateOrderNumberService numberService;
    @Mock
    Tax tax;
    @InjectMocks
    OrderService orderService;

    @Test
    public void createNewOrder() {
        Mockito.when(totalSumCartService.calculateTotalSum()).thenReturn(new BigDecimal("10.00"));
        Mockito.when(numberService.getOrderHaveNumber()).thenReturn(false);
        Mockito.when(numberService.createOrderNumber()).thenReturn("5");
        Mockito.when(tax.getTaxAmount(new BigDecimal("10.00"))).thenReturn(new BigDecimal("2.10"));
        Mockito.when(tax.getAmountWithTax(new BigDecimal("10.00"))).thenReturn(new BigDecimal("12.10"));

        Order newOrder = orderService.createOrder();
        assertEquals("5", newOrder.getNumber());
        assertEquals(new BigDecimal("10.00"), newOrder.getSum());
        assertEquals(new BigDecimal("2.10"), newOrder.getTax());
        assertEquals(new BigDecimal("12.10"), newOrder.getTotal());
    }
}