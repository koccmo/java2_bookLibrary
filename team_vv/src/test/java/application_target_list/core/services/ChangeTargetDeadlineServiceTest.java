package application_target_list.core.services;

import application_target_list.core.database.Database;
import application_target_list.core.database.Target;
import application_target_list.core.requests.ChangeTargetDeadlineRequest;
import application_target_list.core.responses.ChangeTargetDeadlineResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.validators.ChangeTargetDeadlineValidator;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class ChangeTargetDeadlineServiceTest extends TestCase {

    @Mock private Database database;
    @Mock private ChangeTargetDeadlineValidator validator;
    @InjectMocks ChangeTargetDeadlineService service;

    @Test
    public void shouldChangeTargetDeadline() {
        Target target = new Target("name", "description", 1);
        target.setId(1L);
        database.addTarget(target);
        Mockito.when(database.changeTargetDeadline(1L, 100)).thenReturn(true);
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L, 100);
        ChangeTargetDeadlineResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void invalidChangeTargetDeadlineRequest_v1() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L, 100);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;", "no target with that ID"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetDeadlineResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    @Test
    public void invalidChangeTargetDeadlineRequest_v2() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(null, 100);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;", "no target with that ID"));
        errors.add(new CoreError("Target ID;", "must not be empty!"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetDeadlineResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidChangeTargetDeadlineRequest_v3() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(-2L, 100);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;", "no target with that ID"));
        errors.add(new CoreError("Target ID;", "must not be negative!"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetDeadlineResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be negative!");
    }

    @Test
    public void invalidChangeTargetDeadlineRequest_v4() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L, -100);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;", "no target with that ID"));
        errors.add(new CoreError("Target new deadline", "must not be negative!"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetDeadlineResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target new deadline");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be negative!");
    }

    @Test
    public void invalidChangeTargetDeadlineRequest_v5() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(-1L, -100);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;", "no target with that ID"));
        errors.add(new CoreError("Target ID;", "must not be negative!"));
        errors.add(new CoreError("Target new deadline", "must not be negative!"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetDeadlineResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 3);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be negative!");
        assertEquals(response.getErrorList().get(2).getField(), "Target new deadline");
        assertEquals(response.getErrorList().get(2).getMessage(), "must not be negative!");
    }

    @Test
    public void invalidChangeTargetDeadlineRequest_v6() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;", "no target with that ID"));
        errors.add(new CoreError("Target new deadline", "must not be empty!"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetDeadlineResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target new deadline");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty!");
    }
}