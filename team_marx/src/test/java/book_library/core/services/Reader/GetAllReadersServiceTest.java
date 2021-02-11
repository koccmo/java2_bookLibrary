package book_library.core.services.Reader;

import book_library.core.database.Book.BookRepository;
import book_library.core.database.Reader.ReaderRepository;
import book_library.core.domain.Reader;
import book_library.core.requests.Reader.GetAllReaderRequest;
import book_library.core.responses.Reader.GetAllReadersResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetAllReadersServiceTest {

    @Mock
    private ReaderRepository readerRepository;

    @InjectMocks
    private GetAllReadersService service;

    @Test
    public void shouldGetAllReadersFromDB(){
        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("FirstName","LastName", 11111111111L));
        Mockito.when(readerRepository.getAllReaders()).thenReturn(readers);

        GetAllReaderRequest request = new GetAllReaderRequest();
        GetAllReadersResponse response = service.execute(request);
        Mockito.verify(readerRepository).getAllReaders();
        assertFalse(response.hasErrors());
        assertEquals(1, response.getReaders().size());
        assertEquals("FirstName", response.getReaders().get(0).getFirstName());
        assertEquals("LastName", response.getReaders().get(0).getLastName());
        assertEquals(java.util.Optional.of(11111111111L), java.util.Optional.ofNullable(response.getReaders().get(0).getPersonalCode()));
    }

}