package java2.application_target_list.core.services;

import java2.application_target_list.core.database.Database;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.GetAllTargetsRequest;
import java2.application_target_list.core.responses.GetAllTargetsResponse;
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
public class GetAllTargetsServiceTest extends TestCase {

    @Mock private Database database;
    @InjectMocks GetAllTargetsService service;

    @Test
    public void shouldGetTargetsFromDB_v1() {
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name", "description", 1));
        Mockito.when(database.getTargetsList()).thenReturn(targets);
        GetAllTargetsRequest request = new GetAllTargetsRequest();
        GetAllTargetsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 1);
        assertEquals(response.getTargetList().get(0).getName(), "name");
        assertEquals(response.getTargetList().get(0).getDescription(), "description");
    }

    @Test
    public void shouldGetTargetsFromDB_v2() {
        List<Target> targets = new ArrayList<>();
        targets.add(new Target("name", "description", 1));
        targets.add(new Target("n", "d", 4));
        Mockito.when(database.getTargetsList()).thenReturn(targets);
        GetAllTargetsRequest request = new GetAllTargetsRequest();
        GetAllTargetsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 2);
        assertEquals(response.getTargetList().get(0).getName(), "name");
        assertEquals(response.getTargetList().get(0).getDescription(), "description");
        assertEquals(response.getTargetList().get(1).getName(), "n");
        assertEquals(response.getTargetList().get(1).getDescription(), "d");
    }

    @Test
    public void shouldGetTargetsFromDB_v3() {
        List<Target> targets = new ArrayList<>();
        Mockito.when(database.getTargetsList()).thenReturn(targets);
        GetAllTargetsRequest request = new GetAllTargetsRequest();
        GetAllTargetsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getTargetList().size(), 0);
    }
}