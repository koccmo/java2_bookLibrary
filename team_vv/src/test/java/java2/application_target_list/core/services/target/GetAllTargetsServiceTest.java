package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class GetAllTargetsServiceTest {

    private  List<Target>targets;
    @Mock
    private JpaTargetRepository jpaTargetRepository;
    @InjectMocks
    GetAllTargetsService service;

    @BeforeEach
    public void setup() {
        targets = new ArrayList<>();
    }

    @Test
    public void shouldGetTargetsFromDB_v1() {
        targets.add(new Target("name", "description", 1L));
        Mockito.when(jpaTargetRepository.findAll()).thenReturn(targets);
        GetAllTargetsRequest request = new GetAllTargetsRequest();
        GetAllTargetsResponse response = service.execute(request);
        Assertions.assertFalse(response.hasErrors());
        Assertions.assertEquals(response.getTargetList().size(), 1);
        Assertions.assertEquals(response.getTargetList().get(0).getName(), "name");
        Assertions.assertEquals(response.getTargetList().get(0).getDescription(), "description");
    }

    @Test
    public void shouldGetTargetsFromDB_v2() {
        targets.add(new Target("name", "description", 1L));
        targets.add(new Target("n", "d", 4L));
        Mockito.when(jpaTargetRepository.findAll()).thenReturn(targets);
        GetAllTargetsRequest request = new GetAllTargetsRequest();
        GetAllTargetsResponse response = service.execute(request);
        Assertions.assertFalse(response.hasErrors());
        Assertions.assertEquals(response.getTargetList().size(), 2);
        Assertions.assertEquals(response.getTargetList().get(0).getName(), "name");
        Assertions.assertEquals(response.getTargetList().get(0).getDescription(), "description");
        Assertions.assertEquals(response.getTargetList().get(1).getName(), "n");
        Assertions.assertEquals(response.getTargetList().get(1).getDescription(), "d");
    }

    @Test
    public void shouldGetTargetsFromDB_v3() {
        Mockito.when(jpaTargetRepository.findAll()).thenReturn(targets);
        GetAllTargetsRequest request = new GetAllTargetsRequest();
        GetAllTargetsResponse response = service.execute(request);
        Assertions.assertFalse(response.hasErrors());
        Assertions.assertEquals(response.getTargetList().size(), 0);
    }
}