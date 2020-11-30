package application_target_list.core.services;

import application_target_list.core.database.Database;
import application_target_list.core.database.Target;
import application_target_list.core.requests.ChangeTargetNameRequest;
import application_target_list.core.responses.ChangeTargetNameResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.validators.ChangeTargetNameValidator;
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
public class ChangeTargetNameServiceTest extends TestCase {

    @Mock private Database database;
    @Mock private ChangeTargetNameValidator validator;
    @InjectMocks ChangeTargetNameService service;

    @Test
    public void shouldChangeTargetName() {
        Target target = new Target("name", "description", 1);
        target.setId(1L);
        database.addTarget(target);
        Mockito.when(database.changeTargetName(1L, "new name")).thenReturn(true);
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(1L, "new name");
        ChangeTargetNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void invalidChangeTargetNameRequest_v1() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(1L, "new name");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;","no target with that ID"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    @Test
    public void invalidChangeTargetNameRequest_v2() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(null, "new name");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;","no target with that ID"));
        errors.add(new CoreError("Target ID;","must not be empty!"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidChangeTargetNameRequest_v3() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(-2L, "new name");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;","no target with that ID"));
        errors.add(new CoreError("Target ID;","must not be negative!"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be negative!");
    }

    @Test
    public void invalidChangeTargetNameRequest_v4() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(-2L, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;","no target with that ID"));
        errors.add(new CoreError("Target ID;","must not be negative!"));
        errors.add(new CoreError("Target new name","must not be empty!"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 3);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be negative!");
        assertEquals(response.getErrorList().get(2).getField(), "Target new name");
        assertEquals(response.getErrorList().get(2).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidChangeTargetNameRequest_v5() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(2L, "");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;","no target with that ID"));
        errors.add(new CoreError("Target new name","must not be empty!"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        ChangeTargetNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target new name");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty!");
    }
}