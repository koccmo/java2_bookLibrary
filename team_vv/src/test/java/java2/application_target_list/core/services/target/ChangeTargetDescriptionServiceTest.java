package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.ChangeTargetDescriptionRequest;
import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.responses.target.ChangeTargetDescriptionResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.ChangeTargetDescriptionValidator;
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
public class ChangeTargetDescriptionServiceTest extends TestCase {

    private List<CoreError> errors;
    @Mock private JpaTargetRepository jpaTargetRepository;
    @Mock private ChangeTargetDescriptionValidator validator;
    @InjectMocks
    ChangeTargetDescriptionService service;

    @Before
    public void setup() {
        errors = new ArrayList<>();
    }

//    @Test
//    public void shouldChangeTargetDescription() {
//        Target target = new Target("name", "description", 1L);
//        target.setId(1L);
////        targetRepository.addTarget(target);
//        jpaTargetRepository.save(target);
////        Mockito.when(targetRepository.changeTargetDescription(1L, "new description")).thenReturn(true);
//        Mockito.when(jpaTargetRepository.changeTargetDescription(1L, "new description")).thenReturn(1);
//        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(1L, "new description");
//        ChangeTargetDescriptionResponse response = service.execute(request);
//        assertFalse(response.hasErrors());
//    }

    @Test
    public void invalidChangeTargetDescriptionRequest_v1() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(1L, "new description");
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    @Test
    public void invalidChangeTargetDescriptionRequest_v2() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(null, "new description");
        errors.add(new CoreError("Target ID;","must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(1).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidChangeTargetDescriptionRequest_v3() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(-2L, "new description");
        errors.add(new CoreError("Target ID;","must not be negative!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(1).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void invalidChangeTargetDescriptionRequest_v4() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(-2L, null);
        errors.add(new CoreError("Target ID;","must not be negative!"));
        errors.add(new CoreError("Target new description","must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 3);
        assertEquals(response.getErrorList().get(2).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(2).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be negative!");
        assertEquals(response.getErrorList().get(1).getField(), "Target new description");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidChangeTargetDescriptionRequest_v5() {
        ChangeTargetDescriptionRequest request = new ChangeTargetDescriptionRequest(2L, null);
        errors.add(new CoreError("Target new description","must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(1).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(0).getField(), "Target new description");
        assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
    }
}