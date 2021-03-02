package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.responses.target.ChangeTargetDeadlineResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.ChangeTargetDeadlineValidator;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ChangeTargetDeadlineServiceTest {

    private List<CoreError> errors;
    @Mock
    private ChangeTargetDeadlineValidator validator;
    @Mock
    private JpaTargetRepository jpaTargetRepository;
    @InjectMocks
    ChangeTargetDeadlineService service;

    @BeforeEach
    public void setup() {
        errors = new ArrayList<>();
    }

//    @Test
//    public void shouldChangeTargetDeadline() {
//        Target target = new Target("name", "description", 1L);
//        target.setId(1L);
////        targetRepository.addTarget(target);
//        jpaTargetRepository.save(target);
////        Mockito.when(targetRepository.changeTargetDeadline(1L, 100L)).thenReturn(true);
//        Mockito.when(jpaTargetRepository.changeTargetDeadline(1L, 100L)).thenReturn(1);
//        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L, 100L);
//        ChangeTargetDeadlineResponse response = service.execute(request);
////        assertFalse(response.hasErrors());
//
//    }

    @Test
    public void invalidChangeTargetDeadlineRequest_v1() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L, 100L);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetDeadlineResponse response = service.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrorList().size(), 1);
        Assertions.assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        Assertions.assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    @Test
    public void invalidChangeTargetDeadlineRequest_v2() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(null, 100L);
        errors.add(new CoreError("Target ID;", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetDeadlineResponse response = service.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrorList().size(), 2);
        Assertions.assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        Assertions.assertEquals(response.getErrorList().get(1).getMessage(), "no target with that ID");
        Assertions.assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        Assertions.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidChangeTargetDeadlineRequest_v3() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(-2L, 100L);
        errors.add(new CoreError("Target ID;", "must not be negative!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetDeadlineResponse response = service.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrorList().size(), 2);
        Assertions.assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        Assertions.assertEquals(response.getErrorList().get(1).getMessage(), "no target with that ID");
        Assertions.assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        Assertions.assertEquals(response.getErrorList().get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void invalidChangeTargetDeadlineRequest_v4() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L, -100L);
        errors.add(new CoreError("Target new deadline", "must not be negative!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetDeadlineResponse response = service.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrorList().size(), 2);
        Assertions.assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        Assertions.assertEquals(response.getErrorList().get(1).getMessage(), "no target with that ID");
        Assertions.assertEquals(response.getErrorList().get(0).getField(), "Target new deadline");
        Assertions.assertEquals(response.getErrorList().get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void invalidChangeTargetDeadlineRequest_v5() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(-1L, -100L);
        errors.add(new CoreError("Target ID;", "must not be negative!"));
        errors.add(new CoreError("Target new deadline", "must not be negative!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetDeadlineResponse response = service.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrorList().size(), 3);
        Assertions.assertEquals(response.getErrorList().get(2).getField(), "Target ID;");
        Assertions.assertEquals(response.getErrorList().get(2).getMessage(), "no target with that ID");
        Assertions.assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        Assertions.assertEquals(response.getErrorList().get(0).getMessage(), "must not be negative!");
        Assertions.assertEquals(response.getErrorList().get(1).getField(), "Target new deadline");
        Assertions.assertEquals(response.getErrorList().get(1).getMessage(), "must not be negative!");
    }

    @Test
    public void invalidChangeTargetDeadlineRequest_v6() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L, null);
        errors.add(new CoreError("Target new deadline", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetDeadlineResponse response = service.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrorList().size(), 2);
        Assertions.assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        Assertions.assertEquals(response.getErrorList().get(1).getMessage(), "no target with that ID");
        Assertions.assertEquals(response.getErrorList().get(0).getField(), "Target new deadline");
        Assertions.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
    }
}