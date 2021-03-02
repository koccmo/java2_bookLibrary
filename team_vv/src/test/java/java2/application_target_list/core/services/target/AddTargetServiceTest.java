package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.matchers.TargetMatcher;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.AddTargetValidator;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AddTargetServiceTest {

    private List<CoreError> errors;
    @Mock
    private JpaTargetRepository jpaTargetRepository;
    @Mock
    private AddTargetValidator validator;
    @InjectMocks
    AddTargetService service;

    @BeforeEach
    public void setup() {
        errors = new ArrayList<>();
    }

    @Test
    public void shouldAddTargetToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddTargetRequest request = new AddTargetRequest("name", "description", 1L);
        AddTargetResponse response = service.execute(request);
        Assertions.assertFalse(response.hasErrors());
        Mockito.verify(jpaTargetRepository).save(
                argThat(new TargetMatcher("name", "description", 1L)));
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails_v1() {
        AddTargetRequest request = new AddTargetRequest("", "description", 4L);
        errors.add(new CoreError("Target name", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddTargetResponse response = service.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrorList().size(), 1);
        Assertions.assertEquals(response.getErrorList().get(0).getField(), "Target name");
        Assertions.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(jpaTargetRepository);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails_v2() {
        AddTargetRequest request = new AddTargetRequest("name", "", 4L);
        errors.add(new CoreError("Target description", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddTargetResponse response = service.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrorList().size(), 1);
        Assertions.assertEquals(response.getErrorList().get(0).getField(), "Target description");
        Assertions.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(jpaTargetRepository);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails_v3() {
        AddTargetRequest request = new AddTargetRequest("name", "asd", null);
        errors.add(new CoreError("Target deadline", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddTargetResponse response = service.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrorList().size(), 1);
        Assertions.assertEquals(response.getErrorList().get(0).getField(), "Target deadline");
        Assertions.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(jpaTargetRepository);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails_v4() {
        AddTargetRequest request = new AddTargetRequest("name", "sa", -2L);
        errors.add(new CoreError("Target deadline", "must not be negative!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddTargetResponse response = service.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrorList().size(), 1);
        Assertions.assertEquals(response.getErrorList().get(0).getField(), "Target deadline");
        Assertions.assertEquals(response.getErrorList().get(0).getMessage(), "must not be negative!");
        Mockito.verifyNoInteractions(jpaTargetRepository);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails_v5() {
        AddTargetRequest request = new AddTargetRequest("name", "", -2L);
        errors.add(new CoreError("Target description", "must not be empty!"));
        errors.add(new CoreError("Target deadline", "must not be negative!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddTargetResponse response = service.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrorList().size(), 2);
        Assertions.assertEquals(response.getErrorList().get(0).getField(), "Target description");
        Assertions.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        Assertions.assertEquals(response.getErrorList().get(1).getField(), "Target deadline");
        Assertions.assertEquals(response.getErrorList().get(1).getMessage(), "must not be negative!");
        Mockito.verifyNoInteractions(jpaTargetRepository);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails_v6() {
        AddTargetRequest request = new AddTargetRequest(null, "", -2L);
        errors.add(new CoreError("Target name", "must not be empty!"));
        errors.add(new CoreError("Target description", "must not be empty!"));
        errors.add(new CoreError("Target deadline", "must not be negative!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddTargetResponse response = service.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrorList().size(), 3);
        Assertions.assertEquals(response.getErrorList().get(0).getField(), "Target name");
        Assertions.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
        Assertions.assertEquals(response.getErrorList().get(1).getField(), "Target description");
        Assertions.assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty!");
        Assertions.assertEquals(response.getErrorList().get(2).getField(), "Target deadline");
        Assertions.assertEquals(response.getErrorList().get(2).getMessage(), "must not be negative!");
        Mockito.verifyNoInteractions(jpaTargetRepository);
    }
}