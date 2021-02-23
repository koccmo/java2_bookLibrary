package book_library.core.services.Reader;

import book_library.core.database.Reader.ReaderRepository;
import book_library.core.domain.Reader;
import book_library.core.requests.Ordering;
import book_library.core.requests.Paging;
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

@RunWith(MockitoJUnitRunner.class)
public class SearchReadersServiceTest {

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private SearchReadersRequestValidator validator;

    @InjectMocks
    private SearchReadersService service;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);
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

    @Test
    public void shouldSearchByFirstNameAndPersonalCode() {
        SearchReaderRequest request = new SearchReaderRequest("FirstName", null, 11111111111L);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName", "LastName", 11111111111L));
        Mockito.when(readerRepository.findByFirstNameAndPersonalCode("FirstName", 11111111111L)).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getReaders().size());
        assertEquals("FirstName", response.getReaders().get(0).getFirstName());
        assertEquals("LastName", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));

        Mockito.verify(validator).validate(request);
    }

    @Test
    public void shouldSearchByLastNameAndPersonalCode() {
        SearchReaderRequest request = new SearchReaderRequest(null, "LastName", 11111111111L);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName", "LastName", 11111111111L));
        Mockito.when(readerRepository.findByLastNameAndPersonalCode("LastName", 11111111111L)).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getReaders().size());
        assertEquals("FirstName", response.getReaders().get(0).getFirstName());
        assertEquals("LastName", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));

        Mockito.verify(validator).validate(request);
    }

    @Test
    public void shouldSearchByFirstNameAndLastNameAndPersonalCode() {
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", 11111111111L);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName", "LastName", 11111111111L));
        Mockito.when(readerRepository.findByFirstNameAndLastNameAndPersonalCode("FirstName", "LastName", 11111111111L)).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getReaders().size());
        assertEquals("FirstName", response.getReaders().get(0).getFirstName());
        assertEquals("LastName", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));

        Mockito.verify(validator).validate(request);
    }

    @Test
    public void shouldSearchByFirstNameWithOrderingAscendingByLastName() {
        Ordering ordering = new Ordering("lastName", "ASCENDING");
        SearchReaderRequest request = new SearchReaderRequest("FirstName", null, null, ordering);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName", "LastName2", 22222222222L));
        readers.add(new Reader("FirstName", "LastName1", 11111111111L));
        readers.add(new Reader("FirstName", "LastName3", 33333333333L));
        Mockito.when(readerRepository.findByFirstName("FirstName")).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(3, response.getReaders().size());
        assertEquals("FirstName", response.getReaders().get(0).getFirstName());
        assertEquals("LastName1", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));
        assertEquals("FirstName", response.getReaders().get(1).getFirstName());
        assertEquals("LastName2", response.getReaders().get(1).getLastName());
        assertEquals(Optional.of(22222222222L), java.util.Optional.ofNullable(response.getReaders().get(1).getPersonalCode()));
        assertEquals("FirstName", response.getReaders().get(2).getFirstName());
        assertEquals("LastName3", response.getReaders().get(2).getLastName());
        assertEquals(Optional.of(33333333333L), java.util.Optional.ofNullable(response.getReaders().get(2).getPersonalCode()));
    }

    @Test
    public void shouldSearchByFirstNameWithOrderingDescendingByLastName() {
        Ordering ordering = new Ordering("lastName", "DESCENDING");
        SearchReaderRequest request = new SearchReaderRequest("FirstName", null, null, ordering);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName", "LastName2", 22222222222L));
        readers.add(new Reader("FirstName", "LastName1", 11111111111L));
        readers.add(new Reader("FirstName", "LastName3", 33333333333L));
        Mockito.when(readerRepository.findByFirstName("FirstName")).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(3, response.getReaders().size());
        assertEquals("FirstName", response.getReaders().get(0).getFirstName());
        assertEquals("LastName3", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(33333333333L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));
        assertEquals("FirstName", response.getReaders().get(1).getFirstName());
        assertEquals("LastName2", response.getReaders().get(1).getLastName());
        assertEquals(Optional.of(22222222222L), java.util.Optional.ofNullable(response.getReaders().get(1).getPersonalCode()));
        assertEquals("FirstName", response.getReaders().get(2).getFirstName());
        assertEquals("LastName1", response.getReaders().get(2).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(2).getPersonalCode()));
    }

    @Test
    public void shouldSearchByLastNameWithOrderingAscendingByPersonalCode() {
        Ordering ordering = new Ordering("personalCode", "ASCENDING");
        SearchReaderRequest request = new SearchReaderRequest(null, "LastName", null, ordering);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName2", "LastName", 22222222222L));
        readers.add(new Reader("FirstName1", "LastName", 11111111111L));
        readers.add(new Reader("FirstName3", "LastName", 33333333333L));
        Mockito.when(readerRepository.findByLastName("LastName")).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(3, response.getReaders().size());
        assertEquals("FirstName1", response.getReaders().get(0).getFirstName());
        assertEquals("LastName", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));
        assertEquals("FirstName2", response.getReaders().get(1).getFirstName());
        assertEquals("LastName", response.getReaders().get(1).getLastName());
        assertEquals(Optional.of(22222222222L), java.util.Optional.ofNullable(response.getReaders().get(1).getPersonalCode()));
        assertEquals("FirstName3", response.getReaders().get(2).getFirstName());
        assertEquals("LastName", response.getReaders().get(2).getLastName());
        assertEquals(Optional.of(33333333333L), java.util.Optional.ofNullable(response.getReaders().get(2).getPersonalCode()));
    }

    @Test
    public void shouldSearchByPersonalCodeWithOrderingAscendingByFirstName() {
        Ordering ordering = new Ordering("firstName", "ASCENDING");
        SearchReaderRequest request = new SearchReaderRequest(null, null, 11111111111L, ordering);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName2", "LastName2", 11111111111L));
        readers.add(new Reader("FirstName1", "LastName1", 11111111111L));
        readers.add(new Reader("FirstName3", "LastName3", 11111111111L));
        Mockito.when(readerRepository.findByPersonalCode(11111111111L)).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(3, response.getReaders().size());
        assertEquals("FirstName1", response.getReaders().get(0).getFirstName());
        assertEquals("LastName1", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));
        assertEquals("FirstName2", response.getReaders().get(1).getFirstName());
        assertEquals("LastName2", response.getReaders().get(1).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(1).getPersonalCode()));
        assertEquals("FirstName3", response.getReaders().get(2).getFirstName());
        assertEquals("LastName3", response.getReaders().get(2).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(2).getPersonalCode()));
    }

    @Test
    public void shouldSearchByFirstNameAndLastNameWithOrderingAscendingByPersonalCode() {
        Ordering ordering = new Ordering("personalCode", "ASCENDING");
        SearchReaderRequest request = new SearchReaderRequest("FirstName", "LastName", null, ordering);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName", "LastName", 22222222222L));
        readers.add(new Reader("FirstName", "LastName", 11111111111L));
        readers.add(new Reader("FirstName", "LastName", 33333333333L));
        Mockito.when(readerRepository.findByFirstNameAndLastName("FirstName", "LastName")).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(3, response.getReaders().size());
        assertEquals("FirstName", response.getReaders().get(0).getFirstName());
        assertEquals("LastName", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));
        assertEquals("FirstName", response.getReaders().get(1).getFirstName());
        assertEquals("LastName", response.getReaders().get(1).getLastName());
        assertEquals(Optional.of(22222222222L), java.util.Optional.ofNullable(response.getReaders().get(1).getPersonalCode()));
        assertEquals("FirstName", response.getReaders().get(2).getFirstName());
        assertEquals("LastName", response.getReaders().get(2).getLastName());
        assertEquals(Optional.of(33333333333L), java.util.Optional.ofNullable(response.getReaders().get(2).getPersonalCode()));
    }

    @Test
    public void shouldSearchByFirstNameAndPersonalCodeWithOrderingAscendingByLastName() {
        Ordering ordering = new Ordering("lastName", "ASCENDING");
        SearchReaderRequest request = new SearchReaderRequest("FirstName", null, 11111111111L, ordering);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName", "LastName2", 11111111111L));
        readers.add(new Reader("FirstName", "LastName1", 11111111111L));
        readers.add(new Reader("FirstName", "LastName3", 11111111111L));
        Mockito.when(readerRepository.findByFirstNameAndPersonalCode("FirstName", 11111111111L)).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(3, response.getReaders().size());
        assertEquals("FirstName", response.getReaders().get(0).getFirstName());
        assertEquals("LastName1", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));
        assertEquals("FirstName", response.getReaders().get(1).getFirstName());
        assertEquals("LastName2", response.getReaders().get(1).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(1).getPersonalCode()));
        assertEquals("FirstName", response.getReaders().get(2).getFirstName());
        assertEquals("LastName3", response.getReaders().get(2).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(2).getPersonalCode()));
    }

    @Test
    public void shouldSearchByLastNameAndPersonalCodeWithOrderingAscendingByFirstName() {
        Ordering ordering = new Ordering("firstName", "ASCENDING");
        SearchReaderRequest request = new SearchReaderRequest(null, "LastName", 11111111111L, ordering);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName2", "LastName", 11111111111L));
        readers.add(new Reader("FirstName1", "LastName", 11111111111L));
        readers.add(new Reader("FirstName3", "LastName", 11111111111L));
        Mockito.when(readerRepository.findByLastNameAndPersonalCode("LastName", 11111111111L)).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(3, response.getReaders().size());
        assertEquals("FirstName1", response.getReaders().get(0).getFirstName());
        assertEquals("LastName", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));
        assertEquals("FirstName2", response.getReaders().get(1).getFirstName());
        assertEquals("LastName", response.getReaders().get(1).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(1).getPersonalCode()));
        assertEquals("FirstName3", response.getReaders().get(2).getFirstName());
        assertEquals("LastName", response.getReaders().get(2).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(2).getPersonalCode()));
    }

    @Test
    public void shouldSearchByFirstNameWithPagingFirstPage() {
        Paging paging = new Paging(1, 2);
        SearchReaderRequest request = new SearchReaderRequest("FirstName", null, null, paging);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName", "LastName2", 22222222222L));
        readers.add(new Reader("FirstName", "LastName1", 11111111111L));
        readers.add(new Reader("FirstName", "LastName3", 33333333333L));
        Mockito.when(readerRepository.findByFirstName("FirstName")).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(2, response.getReaders().size());
        assertEquals("FirstName", response.getReaders().get(0).getFirstName());
        assertEquals("LastName2", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(22222222222L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));
        assertEquals("FirstName", response.getReaders().get(1).getFirstName());
        assertEquals("LastName1", response.getReaders().get(1).getLastName());
        assertEquals(Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(1).getPersonalCode()));
    }

    @Test
    public void shouldSearchByFirstNameWithPagingSecondPage() {
        Paging paging = new Paging(2, 2);
        SearchReaderRequest request = new SearchReaderRequest("FirstName", null, null, paging);
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName", "LastName2", 22222222222L));
        readers.add(new Reader("FirstName", "LastName1", 11111111111L));
        readers.add(new Reader("FirstName", "LastName3", 33333333333L));
        Mockito.when(readerRepository.findByFirstName("FirstName")).thenReturn(readers);

        SearchReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getReaders().size());
        assertEquals("FirstName", response.getReaders().get(0).getFirstName());
        assertEquals("LastName3", response.getReaders().get(0).getLastName());
        assertEquals(Optional.of(33333333333L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));
    }
}