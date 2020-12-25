package java2.application_target_list.core.services;

import java2.application_target_list.core.requests.DeleteTargetRequest;
import java2.application_target_list.core.database.Database;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.DeleteTargetResponse;
import java2.application_target_list.core.validators.DeleteTargetValidator;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import java.util.ArrayList;


@RunWith(MockitoJUnitRunner.Silent.class)
public class DeleteTargetServiceTest extends TestCase {

    @Mock private Database database;
    @Mock private DeleteTargetValidator validator;
    @InjectMocks DeleteTargetService service;

    @Test
    public void shouldDeleteTarget() {
        Target target = new Target("name", "description", 1);
        target.setId(1L);
        database.addTarget(target);
        Mockito.when(database.isIdInTargetList(1L)).thenReturn(true);
        DeleteTargetRequest request = new DeleteTargetRequest(1L);
        DeleteTargetResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }


    @Test
    public void invalidIdRequest_v1() {
        DeleteTargetRequest request = new DeleteTargetRequest(2L);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;", "no target with that ID"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        DeleteTargetResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    @Test
    public void invalidIdRequest_v2() {
        DeleteTargetRequest request = new DeleteTargetRequest(-2L);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;", "no target with that ID"));
        errors.add(new CoreError("Target ID;", "must not be negative!"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        DeleteTargetResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be negative!");
    }

    @Test
    public void invalidIdRequest_v3() {
        DeleteTargetRequest request = new DeleteTargetRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Target ID;", "no target with that ID"));
        errors.add(new CoreError("Target ID;", "must not be empty!"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        DeleteTargetResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrorList().size(), 2);
        assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
        assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty!");
    }
}