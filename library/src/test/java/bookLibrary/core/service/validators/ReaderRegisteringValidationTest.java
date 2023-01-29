package bookLibrary.core.service.validators;

import bookLibrary.core.dataBase.ReaderRepository;
import bookLibrary.core.request.ReaderRegisteringRequest;
import bookLibrary.core.response.CoreError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class ReaderRegisteringValidationTest {

    @Mock
    private ReaderRepository readerRepository;
    @InjectMocks
    private ReaderRegisteringValidation registeringValidation;

    @Test
    public void nameNotValidEmpty() {
        ReaderRegisteringRequest request = new ReaderRegisteringRequest("", "AAA", "1");
//        ReaderRegisteringValidation registeringValidation = new ReaderRegisteringValidation();
        when(readerRepository.checkByIndexReaderAlreadyRegistered(request.getName(), request.getLastName(),
                Long.parseLong(request.getPersonalCode()))).thenReturn(false);
        List<CoreError> errors = registeringValidation.validate(request, readerRepository);
        assertTrue(!errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("Name", errors.get(0).getField());
        assertEquals("Can`t be Empty or Null!", errors.get(0).getDescription());
    }

    @Test
    public void lastNameNotValidEmpty() {
        ReaderRegisteringRequest request = new ReaderRegisteringRequest("Alex", "", "1");
        when(readerRepository.checkByIndexReaderAlreadyRegistered(request.getName(), request.getLastName(),
                Long.parseLong(request.getPersonalCode()))).thenReturn(false);
        List<CoreError> errors = registeringValidation.validate(request, readerRepository);
        assertTrue(!errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("LastName", errors.get(0).getField());
        assertEquals("Can`t be Empty or Null!", errors.get(0).getDescription());
    }

    @Test
    public void nameNotValidNull() {
        ReaderRegisteringRequest request = new ReaderRegisteringRequest(null, "AAA", "1");
        when(readerRepository.checkByIndexReaderAlreadyRegistered(request.getName(), request.getLastName(),
                Long.parseLong(request.getPersonalCode()))).thenReturn(false);
        List<CoreError> errors = registeringValidation.validate(request, readerRepository);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("Name", errors.get(0).getField());
        assertEquals("Can`t be Empty or Null!", errors.get(0).getDescription());
    }

    @Test
    public void lastNameNotValidNull() {
        ReaderRegisteringRequest request = new ReaderRegisteringRequest("AAA", null, "1");
        when(readerRepository.checkByIndexReaderAlreadyRegistered(request.getName(), request.getLastName(),
                Long.parseLong(request.getPersonalCode()))).thenReturn(false);
        List<CoreError> errors = registeringValidation.validate(request, readerRepository);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("LastName", errors.get(0).getField());
        assertEquals("Can`t be Empty or Null!", errors.get(0).getDescription());
    }

    @Test
    public void nameAndLastNameNotValidEmpty() {
        ReaderRegisteringRequest request = new ReaderRegisteringRequest("", "", "1");
        when(readerRepository.checkByIndexReaderAlreadyRegistered(request.getName(), request.getLastName(),
                Long.parseLong(request.getPersonalCode()))).thenReturn(false);
        List<CoreError> errors = registeringValidation.validate(request, readerRepository);
        assertFalse(errors.isEmpty());
        assertEquals(2, errors.size());
        assertEquals("Name", errors.get(0).getField());
        assertEquals("Can`t be Empty or Null!", errors.get(0).getDescription());
        assertEquals("LastName", errors.get(1).getField());
        assertEquals("Can`t be Empty or Null!", errors.get(1).getDescription());
    }

    @Test
    public void nameAndLastNameNotValidNull() {
        ReaderRegisteringRequest request = new ReaderRegisteringRequest(null, null, "1");
        when(readerRepository.checkByIndexReaderAlreadyRegistered(request.getName(), request.getLastName(),
                Long.parseLong(request.getPersonalCode()))).thenReturn(false);
        List<CoreError> errors = registeringValidation.validate(request, readerRepository);
        assertFalse(errors.isEmpty());
        assertEquals(2, errors.size());
        assertEquals("Name", errors.get(0).getField());
        assertEquals("Can`t be Empty or Null!", errors.get(0).getDescription());
        assertEquals("LastName", errors.get(1).getField());
        assertEquals("Can`t be Empty or Null!", errors.get(1).getDescription());
    }



}