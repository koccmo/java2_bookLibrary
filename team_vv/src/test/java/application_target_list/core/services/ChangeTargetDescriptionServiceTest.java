package application_target_list.core.services;

import application_target_list.core.database.Database;
import application_target_list.core.database.Target;
import application_target_list.core.requests.ChangeTargetDescriptionRequest;
import application_target_list.core.responses.ChangeTargetDescriptionResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.validators.ChangeTargetDescriptionValidator;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ChangeTargetDescriptionServiceTest extends TestCase {

    @Mock private Database database;
    @Mock private ChangeTargetDescriptionValidator validator;
    @InjectMocks ChangeTargetDescriptionService service;


    @Test
    public void shouldChangeTargetDescription() {
        Target target = new Target("name", "description", 1);
        target.setId(1L);
        database.addTarget(target);
        Mockito.when(database.changeTargetDescription(1L, "new description")).thenReturn(true);
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(1L, "new description");
        ChangeTargetDescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void invalidChangeTargetDescriptionRequest_v1() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(1L, "new description");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;","no target with that ID"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    @Test
    public void invalidChangeTargetDescriptionRequest_v2() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(null, "new description");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;","no target with that ID"));
        errors.add(new CoreError("Target ID;","must not be empty!"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidChangeTargetDescriptionRequest_v3() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(-2L, "new description");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;","no target with that ID"));
        errors.add(new CoreError("Target ID;","must not be negative!"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be negative!");
    }

    @Test
    public void invalidChangeTargetDescriptionRequest_v4() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(-2L, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;","no target with that ID"));
        errors.add(new CoreError("Target ID;","must not be negative!"));
        errors.add(new CoreError("Target new description","must not be empty!"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 3);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be negative!");
        assertEquals(response.getErrorList().get(2).getField(), "Target new description");
        assertEquals(response.getErrorList().get(2).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidChangeTargetDescriptionRequest_v5() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(2L, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;","no target with that ID"));
        errors.add(new CoreError("Target new description","must not be empty!"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target new description");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty!");
    }
}