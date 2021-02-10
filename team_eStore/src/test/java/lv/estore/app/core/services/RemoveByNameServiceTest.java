package lv.estore.app.core.services;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.InMemoryDatabaseImpl;
import lv.estore.app.core.request.NameRequest;
import lv.estore.app.core.responses.RemoveResponse;
import lv.estore.app.core.validators.NameValidator;
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
public class RemoveByNameServiceTest {

    @Mock
    NameValidator validator;
    @Mock
    InMemoryDatabaseImpl database;

    @InjectMocks
    RemoveByNameService service;

    @Test
    public void testRemoveByName_Success() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.removeByName("name")).thenReturn(true);
        NameRequest request = new NameRequest("name");
        RemoveResponse response = service.execute(request);

        assertTrue(response.isProductRemoved());
        assertFalse(response.hasErrors());
    }

    @Test
    public void testRemoveById_Error() {
        NameRequest request = new NameRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Field should not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        RemoveResponse response = service.execute(request);

        assertTrue(response.hasErrors());
        assertTrue(response.getErrors().size() != 0);
        assertTrue(response.getErrors()
                .stream().anyMatch(s -> s.getField().equals("Name")
                        && s.getMessage().equals("Field should not be empty")));
    }
}