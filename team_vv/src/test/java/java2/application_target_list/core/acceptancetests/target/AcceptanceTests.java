package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.target.*;
import java2.application_target_list.core.responses.target.*;
import java2.application_target_list.core.services.target.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@SpringBootTest
public class AcceptanceTests {

    @Autowired
    private ChangeTargetNameService changeTargetNameService;
    @Autowired
    private AddTargetService addTargetService;
    @Autowired
    private ChangeTargetDescriptionService changeTargetDescriptionService;
    @Autowired
    private ChangeTargetDeadlineService changeTargetDeadlineService;
    @Autowired
    private SearchTargetByNameService searchTargetByNameService;
    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private DeleteTargetService deleteTargetService;
    @Autowired
    private SearchTargetByDescriptionService searchTargetByDescriptionService;

    private Long targetId;

    @BeforeEach
    public void setup() {
        databaseCleaner.clean();
    }

    @Test
    public void test() {
        addTargetsToDB();
        deleteFirstTargetFromDB();
        changeThirdTargetParameters();
        searchTargetsByName();
        searchTargetsByDescription();
    }

    private void searchTargetsByDescription() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description");
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assertions.assertFalse(searchTargetByDescriptionResponse.hasErrors());
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name2");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name3");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(6L));
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "New name");
        Assertions.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "New description");
        Assertions.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(100L));


    }

    private void searchTargetsByName() {
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("New name");
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assertions.assertNull(searchTargetByNameResponse.getErrorList());
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "New name");
        Assertions.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "New description");
        Assertions.assertEquals(java.util.Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(100L));
    }


    private void changeThirdTargetParameters() {
        targetId = getAllTargetsService.execute(new GetAllTargetsRequest()).getTargetList().get(2).getId();
        ChangeTargetNameRequest changeTargetNameRequest = new ChangeTargetNameRequest(targetId, "New name");
        ChangeTargetNameResponse changeTargetNameResponse = changeTargetNameService.execute(changeTargetNameRequest);

        ChangeTargetDeadlineRequest changeTargetDeadlineRequest = new ChangeTargetDeadlineRequest(targetId, 100L);
        ChangeTargetDeadlineResponse changeTargetDeadlineResponse = changeTargetDeadlineService.execute(changeTargetDeadlineRequest);

        ChangeTargetDescriptionRequest changeTargetDescriptionRequest = new ChangeTargetDescriptionRequest(targetId, "New description");
        ChangeTargetDescriptionResponse changeTargetDescriptionResponse = changeTargetDescriptionService.execute(changeTargetDescriptionRequest);

        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assertions.assertNull(changeTargetNameResponse.getErrorList());
        Assertions.assertNull(changeTargetDeadlineResponse.getErrorList());
        Assertions.assertNull(changeTargetDescriptionResponse.getErrorList());
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().size(), 3);
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(2).getName(), "New name");
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(2).getDescription(), "New description");
        Assertions.assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(2).getDeadline()), Optional.of(100L));
    }

    private void deleteFirstTargetFromDB(){
        targetId = getAllTargetsService.execute(new GetAllTargetsRequest()).getTargetList().get(0).getId();

        DeleteTargetRequest deleteTargetRequest = new DeleteTargetRequest(targetId);
        DeleteTargetResponse deleteTargetResponse = deleteTargetService.execute(deleteTargetRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assertions.assertFalse(deleteTargetResponse.hasErrors());
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().size(), 3);
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name2");
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description2");
        Assertions.assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(0).getDeadline()), Optional.of(4L));
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(1).getName(), "name3");
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(1).getDescription(), "description3");
        Assertions.assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(1).getDeadline()), Optional.of(6L));
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(2).getName(), "wdc");
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(2).getDescription(), "sda");
        Assertions.assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(2).getDeadline()), Optional.of(156L));
    }

    private void addTargetsToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name1", "description1", 1L);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 4L);
        AddTargetRequest addTargetRequest3 = new AddTargetRequest("name3", "description3", 6L);
        AddTargetRequest addTargetRequest4 = new AddTargetRequest("wdc", "sda", 156L);
        AddTargetResponse addTargetResponse = addTargetService.execute(addTargetRequest1);
        Assertions.assertFalse(addTargetResponse.hasErrors());
        addTargetResponse = addTargetService.execute(addTargetRequest2);
        Assertions.assertFalse(addTargetResponse.hasErrors());
        addTargetResponse = addTargetService.execute(addTargetRequest3);
        Assertions.assertFalse(addTargetResponse.hasErrors());
        addTargetResponse = addTargetService.execute(addTargetRequest4);
        Assertions.assertFalse(addTargetResponse.hasErrors());
    }
}
