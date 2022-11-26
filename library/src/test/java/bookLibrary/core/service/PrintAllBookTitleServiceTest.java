package bookLibrary.core.service;

import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.PrintAllBooksTitleRequest;
import bookLibrary.core.response.PrintAllBooksTitleResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrintAllBookTitleServiceTest {
    @Mock private DataBase dataBase;
    @InjectMocks private PrintAllBookTitleService service;

    @Test
    public void testWhenBookListIsEmpty() {
        PrintAllBooksTitleRequest request = new PrintAllBooksTitleRequest();
        PrintAllBooksTitleResponse response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(0, response.getBookList().size());
    }

    @Test
    public void shouldGetBookFromDataBase() {
        List<String> titles = new ArrayList<>();
        String title = "Title";
        titles.add(title);

        PrintAllBooksTitleRequest request = new PrintAllBooksTitleRequest();
        when(dataBase.getAllBooksTitle()).thenReturn(titles);
        PrintAllBooksTitleResponse response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals("Title", response.getBookList().get(0));
        assertEquals(1, response.getBookList().size());
    }

    }
