package lv.estore.app.core.services;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.InMemoryDatabaseImpl;
import lv.estore.app.core.request.IdRequest;
import lv.estore.app.core.responses.RemoveResponse;
import lv.estore.app.core.validators.IdValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class RemoveByIdServiceTest {

    @Mock
    IdValidator validator;
    @Mock
    InMemoryDatabaseImpl database;

    @InjectMocks
    RemoveByIdService service;

    @Test
    public void testRemoveById_Success() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.removeById(1L)).thenReturn(true);
        IdRequest request = new IdRequest("1");
        RemoveResponse response = service.execute(request);

        assertTrue(response.isProductRemoved());
        assertFalse(response.hasErrors());
    }

    @Test
    public void testRemoveById_Error() {
        IdRequest request = new IdRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Id", "Field should not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        RemoveResponse response = service.execute(request);

        assertTrue(response.hasErrors());
        assertTrue(response.getErrors().size() != 0);
        assertTrue(response.getErrors()
                .stream().anyMatch(s -> s.getField().equals("Id")
                                        && s.getMessage().equals("Field should not be empty")));
    }
}