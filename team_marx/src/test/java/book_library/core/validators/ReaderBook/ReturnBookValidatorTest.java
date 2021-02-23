package book_library.core.validators.ReaderBook;

import book_library.core.database.Book.BookRepository;
import book_library.core.database.Reader.ReaderRepository;
import book_library.core.database.ReaderBook.ReaderBookRepository;
import book_library.core.requests.ReaderBook.ReturnBookRequest;
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
public class ReturnBookValidatorTest {

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ReaderBookRepository readerBookRepository;

    @InjectMocks
    private ReturnBookValidator validator;

    @Test
    public void success() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookReturnDate = formatter1.parse("9020/01/01 14:45");
        ReturnBookRequest request = new ReturnBookRequest(1L,1L, bookReturnDate);
        Mockito.when(readerRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(bookRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(readerBookRepository.isBookInLibrary(any())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
        Mockito.verify(readerRepository).isSuchIdPresentsInDatabase(1L);
        Mockito.verify(bookRepository).isSuchIdPresentsInDatabase(1L);
        Mockito.verify(readerBookRepository).isBookInLibrary(1L);
    }

    @Test
    public void shouldReturnErrorWhenReaderIdIsNull() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookReturnDate = formatter1.parse("9020/01/01 14:45");
        ReturnBookRequest request = new ReturnBookRequest(null,1L, bookReturnDate);
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
        Date bookReturnDate = formatter1.parse("9020/01/01 14:45");
        ReturnBookRequest request = new ReturnBookRequest(1L,null, bookReturnDate);
        List<CoreError> errors = validator.validate(request);
        Mockito.verifyNoInteractions(readerRepository);
        Mockito.verifyNoInteractions(bookRepository);
        Mockito.verifyNoInteractions(readerBookRepository);
        assertEquals(1, errors.size());
        assertEquals("bookId", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenBookReturnDataIsNull() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookReturnDate = formatter1.parse("9020/01/01 14:45");
        ReturnBookRequest request = new ReturnBookRequest(1L,1L, null);
        List<CoreError> errors = validator.validate(request);
        Mockito.verifyNoInteractions(readerRepository);
        Mockito.verifyNoInteractions(bookRepository);
        Mockito.verifyNoInteractions(readerBookRepository);
        assertEquals(1, errors.size());
        assertEquals("bookReturnDate", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenReaderIdIsNotPresenceInReaderRepository() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookReturnDate = formatter1.parse("9020/01/01 14:45");
        ReturnBookRequest request = new ReturnBookRequest(1L,1L, bookReturnDate);
        Mockito.when(readerRepository.isSuchIdPresentsInDatabase(any())).thenReturn(false);
        Mockito.when(bookRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("readerId", errors.get(0).getField());
        assertEquals("No reader with such id is present in database!", errors.get(0).getMessage());
        Mockito.verify(readerRepository).isSuchIdPresentsInDatabase(1L);
        Mockito.verify(bookRepository).isSuchIdPresentsInDatabase(1L);
    }

    @Test
    public void shouldReturnErrorWhenBookIdIsNotPresenceInBookRepository() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookReturnDate = formatter1.parse("9020/01/01 14:45");
        ReturnBookRequest request = new ReturnBookRequest(1L,1L, bookReturnDate);
        Mockito.when(readerRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(bookRepository.isSuchIdPresentsInDatabase(any())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("bookId", errors.get(0).getField());
        assertEquals("No book with such id is present in database!", errors.get(0).getMessage());
        Mockito.verify(readerRepository).isSuchIdPresentsInDatabase(1L);
        Mockito.verify(bookRepository).isSuchIdPresentsInDatabase(1L);
    }

    @Test
    public void shouldReturnErrorWhenDateAndTimeEnteredIsBeforeNow() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookReturnDate = formatter1.parse("1020/01/01 14:45");
        ReturnBookRequest request = new ReturnBookRequest(1L,1L, bookReturnDate);
        Mockito.when(readerRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(bookRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("bookReturnDate", errors.get(0).getField());
        assertEquals("bookReturnDate and time cannot be earlier than the current date and time.", errors.get(0).getMessage());
        Mockito.verify(readerRepository).isSuchIdPresentsInDatabase(1L);
        Mockito.verify(bookRepository).isSuchIdPresentsInDatabase(1L);
    }

    @Test
    public void shouldReturnErrorWhenBookIsInLibrary() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookReturnDate = formatter1.parse("9020/01/01 14:45");
        ReturnBookRequest request = new ReturnBookRequest(1L,1L, bookReturnDate);
        Mockito.when(readerRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(bookRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(readerBookRepository.isBookInLibrary(any())).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("bookId", errors.get(0).getField());
        assertEquals("This book is already in Library.", errors.get(0).getMessage());
        Mockito.verify(readerRepository).isSuchIdPresentsInDatabase(1L);
        Mockito.verify(bookRepository).isSuchIdPresentsInDatabase(1L);
        Mockito.verify(readerBookRepository).isBookInLibrary(1L);
    }
}