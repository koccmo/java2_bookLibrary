package bookLibrary.core.service;

import bookLibrary.core.dataBase.ReaderRepository;
import bookLibrary.core.domain.Reader;
import bookLibrary.core.request.SearchReaderRequest;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.response.SearchReaderResponse;
import bookLibrary.core.service.validators.ReaderSearchFieldValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class ReaderSearchServiceTest {
    @Mock
    private ReaderRepository dataBase;
    @Mock
    private ReaderSearchFieldValidator validator;
    @InjectMocks
    private ReaderSearchService service;

    @Test
    public void shouldSearchByName() {
        SearchReaderRequest request = new SearchReaderRequest("A", "");
        when(validator.validate(request)).thenReturn(List.of());

        when(dataBase.findByName(request.getName())).thenReturn(List.of(
                new Reader("A", "B", 1L)));
        SearchReaderResponse response = service.execute(request);

        assertFalse(response.getReaderList().isEmpty());
        assertEquals("A", response.getReaderList().get(0).getFirstName());
        assertEquals("B", response.getReaderList().get(0).getLastName());
        assertEquals(Long.valueOf(1L), response.getReaderList().get(0).getPersonal_code());
    }

    @Test
    public void shouldSearchByLastName() {
        SearchReaderRequest request = new SearchReaderRequest("", "B");
        when(validator.validate(request)).thenReturn(List.of());
        when(dataBase.findByLastName(request.getLastName())).thenReturn(List.of(
                new Reader("A", "B", 2L)
        ));
        SearchReaderResponse response = service.execute(request);

        assertFalse(response.getReaderList().isEmpty());
        assertEquals(1, response.getReaderList().size());
        assertEquals("A", response.getReaderList().get(0).getFirstName());
        assertEquals("B", response.getReaderList().get(0).getLastName());
        assertEquals(Long.valueOf(2L), response.getReaderList().get(0).getPersonal_code());
    }

    @Test
    public void shouldSearchByNameAndLastName() {
        SearchReaderRequest request = new SearchReaderRequest("A", "B");
        when(validator.validate(request)).thenReturn(List.of());
        when(dataBase.findByNameAndLastName(request.getName(), request.getLastName()))
                .thenReturn(List.of(new Reader("A", "B", 3L)));
        SearchReaderResponse response = service.execute(request);
        assertFalse(response.hasError());
        assertFalse(response.getReaderList().isEmpty());
        assertEquals("A", response.getReaderList().get(0).getFirstName());
        assertEquals("B", response.getReaderList().get(0).getLastName());
        assertEquals(Long.valueOf(3L), response.getReaderList().get(0).getPersonal_code());
    }

    @Test
    public void shouldReturnResponseWithErrorWhenNameAndLastNameEmpty() {
        SearchReaderRequest request = new SearchReaderRequest("", "");
        when(validator.validate(request)).thenReturn(List.of(
                new CoreError("Name and LastName", "One of them must be fill UP!")));
        SearchReaderResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals("Name and LastName", response.getErrors().get(0).getField());
        assertEquals("One of them must be fill UP!", response.getErrors().get(0).getDescription());
        assertEquals(1, response.getErrors().size());
    }

    @Test
    public void shouldReturnResponseWithErrorWhenNameContainNumbers() {
        SearchReaderRequest request = new SearchReaderRequest("A1", "");
        when(validator.validate(request)).thenReturn(List.of(
                new CoreError("Name", "Name must contain only Letters")));
        SearchReaderResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals("Name", response.getErrors().get(0).getField());
        assertEquals("Name must contain only Letters", response.getErrors().get(0).getDescription());
        assertEquals(1, response.getErrors().size());
    }

    @Test
    public void shouldReturnResponseWithErrorWhenLastNameContainNumbers() {
        SearchReaderRequest request = new SearchReaderRequest("", "B123");
        when(validator.validate(request)).thenReturn(List.of(
                new CoreError("LastName", "LastName must contain only Letters!")));
        SearchReaderResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals("LastName", response.getErrors().get(0).getField());
        assertEquals("LastName must contain only Letters!", response.getErrors().get(0).getDescription());
        assertEquals(1, response.getErrors().size());
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenNameAndLastNameContainNumbers() {
        SearchReaderRequest request = new SearchReaderRequest("A12", "B32");
        when(validator.validate(request)).thenReturn(List.of(
                new CoreError("Name", "Name must contain only Letters!"),
                new CoreError("LastName", "LastName must contain only Letters!")));
        SearchReaderResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(2, response.getErrors().size());
        assertEquals("Name", response.getErrors().get(0).getField());
        assertEquals("Name must contain only Letters!", response.getErrors().get(0).getDescription());
        assertEquals("LastName", response.getErrors().get(1).getField());
        assertEquals("LastName must contain only Letters!", response.getErrors().get(1).getDescription());
    }
}