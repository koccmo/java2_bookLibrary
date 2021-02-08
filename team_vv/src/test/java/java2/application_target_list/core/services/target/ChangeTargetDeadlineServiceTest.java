package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.responses.target.ChangeTargetDeadlineResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.ChangeTargetDeadlineValidator;
import junit.framework.TestCase;
import org.junit.Before;
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

    private List<CoreError> errors;
    @Mock private TargetRepository targetRepository;
    @Mock private ChangeTargetDeadlineValidator validator;
    @InjectMocks
    ChangeTargetDeadlineService service;

    @Before
    public void setup() {
        errors = new ArrayList<>();
    }

    @Test
    public void shouldChangeTargetDeadline() {
        Target target = new Target("name", "description", 1L);
        target.setId(1L);
        targetRepository.addTarget(target);
        Mockito.when(targetRepository.changeTargetDeadline(1L, 100L)).thenReturn(true);
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L, 100L);
        ChangeTargetDeadlineResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void invalidChangeTargetDeadlineRequest_v1() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L, 100L);
        errors.add(new CoreError("Target ID;", "no target with that ID"));
        Mockito.when(validator.validate(request, targetRepository)).thenReturn(errors);
        ChangeTargetDeadlineResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    @Test
    public void invalidChangeTargetDeadlineRequest_v2() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(null, 100L);
        errors.add(new CoreError("Target ID;", "no target with that ID"));
        errors.add(new CoreError("Target ID;", "must not be empty!"));
        Mockito.when(validator.validate(request, targetRepository)).thenReturn(errors);
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
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(-2L, 100L);
        errors.add(new CoreError("Target ID;", "no target with that ID"));
        errors.add(new CoreError("Target ID;", "must not be negative!"));
        Mockito.when(validator.validate(request, targetRepository)).thenReturn(errors);
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
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L, -100L);
        errors.add(new CoreError("Target ID;", "no target with that ID"));
        errors.add(new CoreError("Target new deadline", "must not be negative!"));
        Mockito.when(validator.validate(request, targetRepository)).thenReturn(errors);
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
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(-1L, -100L);
        errors.add(new CoreError("Target ID;", "no target with that ID"));
        errors.add(new CoreError("Target ID;", "must not be negative!"));
        errors.add(new CoreError("Target new deadline", "must not be negative!"));
        Mockito.when(validator.validate(request, targetRepository)).thenReturn(errors);
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
        errors.add(new CoreError("Target ID;", "no target with that ID"));
        errors.add(new CoreError("Target new deadline", "must not be empty!"));
        Mockito.when(validator.validate(request, targetRepository)).thenReturn(errors);
        ChangeTargetDeadlineResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target new deadline");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty!");
    }
}