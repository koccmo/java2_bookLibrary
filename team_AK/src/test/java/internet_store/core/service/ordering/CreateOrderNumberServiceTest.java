package internet_store.core.service.ordering;

import internet_store.core.service.date_formats.DateCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateOrderNumberServiceTest {
    @Mock
    DateCreator dateCreator;
    @InjectMocks
    CreateOrderNumberService numberService;

    @Test
    public void createFullOrderNumber() {
        Mockito.lenient().when(dateCreator.createShortDateFormat()).thenReturn("08022021");
        numberService.setOrderNumber(1);
        numberService.createOrderNumber();

        assertEquals("08022021/1", numberService.getFullOrderNumber());
    }
}