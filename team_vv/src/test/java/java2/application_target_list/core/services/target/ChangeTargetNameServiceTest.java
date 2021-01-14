package java2.application_target_list.core.services.target;

import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import java2.application_target_list.core.database.TargetDatabase;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.responses.target.ChangeTargetNameResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.services.target.ChangeTargetNameService;
import java2.application_target_list.core.validators.target.ChangeTargetNameValidator;
import junit.framework.TestCase;
import org.junit.Before;
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

    private List<CoreError> errors;
    @Mock private TargetDatabase targetDatabase;
    @Mock private ChangeTargetNameValidator validator;
    @InjectMocks
    ChangeTargetNameService service;

    @Before
    public void setup() {
        errors = new ArrayList<>();
    }

    @Test
    public void shouldChangeTargetName() {
        Mockito.when(targetDatabase.changeTargetName(1L, "new name")).thenReturn(true);
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(1L, "new name");
        ChangeTargetNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void invalidChangeTargetNameRequest_v1() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(1L, "new name");
        errors.add(new CoreError("Target ID;","no target with that ID"));
        Mockito.when(validator.validate(request, targetDatabase)).thenReturn(errors);
        ChangeTargetNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    @Test
    public void invalidChangeTargetNameRequest_v2() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(null, "new name");
        errors.add(new CoreError("Target ID;","no target with that ID"));
        errors.add(new CoreError("Target ID;","must not be empty!"));
        Mockito.when(validator.validate(request, targetDatabase)).thenReturn(errors);
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
        errors.add(new CoreError("Target ID;","no target with that ID"));
        errors.add(new CoreError("Target ID;","must not be negative!"));
        Mockito.when(validator.validate(request, targetDatabase)).thenReturn(errors);
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
        errors.add(new CoreError("Target ID;","no target with that ID"));
        errors.add(new CoreError("Target ID;","must not be negative!"));
        errors.add(new CoreError("Target new name","must not be empty!"));
        Mockito.when(validator.validate(request, targetDatabase)).thenReturn(errors);
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
        errors.add(new CoreError("Target ID;","no target with that ID"));
        errors.add(new CoreError("Target new name","must not be empty!"));
        Mockito.when(validator.validate(request, targetDatabase)).thenReturn(errors);
        ChangeTargetNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target new name");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty!");
    }
}