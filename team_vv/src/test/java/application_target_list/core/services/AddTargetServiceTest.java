package application_target_list.core.services;

import application_target_list.core.database.Database;
import application_target_list.core.matchers.TargetMatcher;
import application_target_list.core.requests.AddTargetRequest;
import application_target_list.core.responses.AddTargetResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.validators.AddTargetValidator;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class AddTargetServiceTest extends TestCase {

    @Mock private Database database;
    @Mock private AddTargetValidator validator;
    @InjectMocks AddTargetService service;

    @Test
    public void shouldAddTargetToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddTargetRequest request = new AddTargetRequest("name", "description", 1);
        AddTargetResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(database).addTarget(
                argThat(new TargetMatcher("name", "description", 1)));
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails_v1() {
        AddTargetRequest request = new AddTargetRequest("", "description", 4);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target name", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddTargetResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Target name");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails_v2() {
        AddTargetRequest request = new AddTargetRequest("name", "", 4);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target description", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddTargetResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Target description");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails_v3() {
        AddTargetRequest request = new AddTargetRequest("name", "asd", null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target deadline", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddTargetResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Target deadline");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails_v4() {
        AddTargetRequest request = new AddTargetRequest("name", "sa", -2);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target deadline", "must not be negative!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddTargetResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Target deadline");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be negative!");
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails_v5() {
        AddTargetRequest request = new AddTargetRequest("name", "", -2);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target description", "must not be empty!"));
        errors.add(new CoreError("Target deadline", "must not be negative!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddTargetResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target description");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(response.getErrorList().get(1).getField(), "Target deadline");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be negative!");
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails_v6() {
        AddTargetRequest request = new AddTargetRequest(null, "", -2);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target name", "must not be empty!"));
        errors.add(new CoreError("Target description", "must not be empty!"));
        errors.add(new CoreError("Target deadline", "must not be negative!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddTargetResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 3);
        assertEquals(response.getErrorList().get(0).getField(), "Target name");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(response.getErrorList().get(1).getField(), "Target description");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty!");
        assertEquals(response.getErrorList().get(2).getField(), "Target deadline");
        assertEquals(response.getErrorList().get(2).getMessage(), "must not be negative!");
        Mockito.verifyNoInteractions(database);
    }


}