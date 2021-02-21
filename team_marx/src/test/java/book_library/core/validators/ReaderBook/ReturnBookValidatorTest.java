package book_library.core.validators.ReaderBook;

import book_library.core.database.Book.BookRepository;
import book_library.core.database.Reader.ReaderRepository;
import book_library.core.database.ReaderBook.ReaderBookRepository;
import book_library.core.requests.ReaderBook.ReturnBookRequest;
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
}