package book_library.core.services.Reader;

import book_library.core.database.Reader.ReaderRepository;
import book_library.core.domain.Reader;
import book_library.core.requests.Reader.SearchReaderRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.Reader.SearchReadersResponse;
import book_library.core.validators.Reader.SearchReadersRequestValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SearchReadersServiceTest {

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private SearchReadersRequestValidator validator;

    @InjectMocks
    private SearchReadersService service;

    @Before
    public void setup(){
        ReflectionTestUtils.setField(service,"orderingEnabled", true);
        ReflectionTestUtils.setField(service,"pagingEnabled", true);
    }

    @Test
    public void shouldReturnResponseWithErrorWhenValidatorFails() {
        SearchReaderRequest request = new SearchReaderRequest(null, null, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("firstName", "Must not be empty!"));
        errors.add(new CoreError("lastName", "Must not be empty!"));
        errors.add(new CoreError("personalCode", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        SearchReadersResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(3, response.getErrors().size());
        assertEquals("firstName", response.getErrors().get(0).getField());
        assertEquals("Must not be empty!", response.getErrors().get(0).getMessage());
        assertEquals("lastName", response.getErrors().get(1).getField());
        assertEquals("Must not be empty!", response.getErrors().get(1).getMessage());
        assertEquals("personalCode", response.getErrors().get(2).getField());
        assertEquals("Must not be empty!", response.getErrors().get(2).getMessage());

        Mockito.verify(validator).validate(request);
        Mockito.verifyNoInteractions(readerRepository);
    }

    @Test
    public void shouldSearchByFirstName() {
        SearchReaderRequest request = new SearchReaderRequest("FirstName", null, null);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName", "LastName", 11111111111L));
        Mockito.when(readerRepository.findByFirstName("FirstName")).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getReaders().size());
        assertEquals("FirstName", response.getReaders().get(0).getFirstName());
        assertEquals("LastName", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));

        Mockito.verify(validator).validate(request);
    }

    @Test
    public void shouldSearchByLastName() {
        SearchReaderRequest request = new SearchReaderRequest(null, "LastName", null);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName", "LastName", 11111111111L));
        Mockito.when(readerRepository.findByLastName("LastName")).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getReaders().size());
        assertEquals("FirstName", response.getReaders().get(0).getFirstName());
        assertEquals("LastName", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));

        Mockito.verify(validator).validate(request);
    }

    @Test
    public void shouldSearchByPersonalCode() {
        SearchReaderRequest request = new SearchReaderRequest(null, null, 11111111111L);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName", "LastName", 11111111111L));
        Mockito.when(readerRepository.findByPersonalCode(11111111111L)).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getReaders().size());
        assertEquals("FirstName", response.getReaders().get(0).getFirstName());
        assertEquals("LastName", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));

        Mockito.verify(validator).validate(request);
    }

    @Test
    public void shouldSearchByFirstNameAndLastName() {
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", null);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName", "LastName", 11111111111L));
        Mockito.when(readerRepository.findByFirstNameAndLastName("FirstName", "LastName")).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getReaders().size());
        assertEquals("FirstName", response.getReaders().get(0).getFirstName());
        assertEquals("LastName", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));

        Mockito.verify(validator).validate(request);
    }
}