package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.target.DeleteTargetResponse;
import java2.application_target_list.core.validators.target.DeleteTargetValidator;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class DeleteTargetServiceTest {

    private List<CoreError> errors;
    @Mock
    private DeleteTargetValidator validator;
    @Mock
    private JpaTargetRepository jpaTargetRepository;
    @InjectMocks
    DeleteTargetService service;

    @BeforeEach
    public void setup() {
        errors = new ArrayList<>();
    }

    @Test
    public void shouldDeleteTarget() {
        Mockito.when(jpaTargetRepository.existsById(1L)).thenReturn(true);
        DeleteTargetRequest request = new DeleteTargetRequest(1L);
        DeleteTargetResponse response = service.execute(request);
        Assertions.assertFalse(response.hasErrors());
    }


    @Test
    public void invalidIdRequest_v1() {
        DeleteTargetRequest request = new DeleteTargetRequest(2L);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteTargetResponse response = service.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrorList().size(), 1);
        Assertions.assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        Assertions.assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    @Test
    public void invalidIdRequest_v2() {
        DeleteTargetRequest request = new DeleteTargetRequest(-2L);
        errors.add(new CoreError("Target ID;", "must not be negative!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteTargetResponse response = service.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrorList().size(), 2);
        Assertions.assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        Assertions.assertEquals(response.getErrorList().get(1).getMessage(), "no target with that ID");
        Assertions.assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        Assertions.assertEquals(response.getErrorList().get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void invalidIdRequest_v3() {
        DeleteTargetRequest request = new DeleteTargetRequest(null);
        errors.add(new CoreError("Target ID;", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteTargetResponse response = service.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrorList().size(), 2);
        Assertions.assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        Assertions.assertEquals(response.getErrorList().get(1).getMessage(), "no target with that ID");
        Assertions.assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        Assertions.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
    }
}