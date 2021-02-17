package book_library.core.validators.ReaderBook;

import book_library.core.database.Book.BookRepository;
import book_library.core.database.Reader.ReaderRepository;
import book_library.core.database.ReaderBook.ReaderBookRepository;
import book_library.core.requests.ReaderBook.TakeBookRequest;
import book_library.core.responses.CoreError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class TakeBookValidatorTest {

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ReaderBookRepository readerBookRepository;

    @InjectMocks
    private TakeBookValidator validator;

    @Test
    public void success() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookOutDate = formatter1.parse("2020/01/01 14:45");
        TakeBookRequest request = new TakeBookRequest(1L,1L, bookOutDate);
        Mockito.when(readerRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(bookRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(readerBookRepository.isBookInLibrary(any())).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
        Mockito.verify(readerRepository).isSuchIdPresentsInDatabase(1L);
        Mockito.verify(bookRepository).isSuchIdPresentsInDatabase(1L);
        Mockito.verify(readerBookRepository).isBookInLibrary(1L);
    }

    @Test
    public void shouldReturnErrorWhenReaderIdIsNull() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookOutDate = formatter1.parse("2020/01/01 14:45");
        TakeBookRequest request = new TakeBookRequest(null,1L, bookOutDate);
        Mockito.when(readerRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(bookRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(readerBookRepository.isBookInLibrary(any())).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        Mockito.verifyNoInteractions(readerRepository);
        Mockito.verifyNoInteractions(bookRepository);
        Mockito.verifyNoInteractions(readerBookRepository);
        assertEquals(1, errors.size());
        assertEquals("readerId", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenBookIdIsNull() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookOutDate = formatter1.parse("2020/01/01 14:45");
        TakeBookRequest request = new TakeBookRequest(1L,null, bookOutDate);
        Mockito.when(readerRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(bookRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(readerBookRepository.isBookInLibrary(any())).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        Mockito.verifyNoInteractions(readerRepository);
        Mockito.verifyNoInteractions(bookRepository);
        Mockito.verifyNoInteractions(readerBookRepository);
        assertEquals(1, errors.size());
        assertEquals("bookId", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenBookOutDataIsNull() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookOutDate = formatter1.parse("2020/01/01 14:45");
        TakeBookRequest request = new TakeBookRequest(1L,1L, null);
        Mockito.when(readerRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(bookRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(readerBookRepository.isBookInLibrary(any())).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        Mockito.verifyNoInteractions(readerRepository);
        Mockito.verifyNoInteractions(bookRepository);
        Mockito.verifyNoInteractions(readerBookRepository);
        assertEquals(1, errors.size());
        assertEquals("bookOutDate", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }
}