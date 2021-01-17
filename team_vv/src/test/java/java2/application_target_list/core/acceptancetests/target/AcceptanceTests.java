package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.requests.target.*;
import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.responses.target.*;
import java2.application_target_list.core.services.target.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AcceptanceTests {


    private ChangeTargetNameService changeTargetNameService;
    private AddTargetService addTargetService;
    private ChangeTargetDescriptionService changeTargetDescriptionService;
    private ChangeTargetDeadlineService changeTargetDeadlineService;
    private SearchTargetByNameService searchTargetByNameService;
    private AddTargetResponse addTargetResponse;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        changeTargetNameService = applicationContext.getBean(ChangeTargetNameService.class);
        addTargetService = applicationContext.getBean(AddTargetService.class);
        changeTargetDescriptionService = applicationContext.getBean(ChangeTargetDescriptionService.class);
        changeTargetDeadlineService = applicationContext.getBean(ChangeTargetDeadlineService.class);
        searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);

    }

    @Test
    public void test() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name1", "description1", 1);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 4);
        AddTargetRequest addTargetRequest3 = new AddTargetRequest("name3", "description3", 6);
        AddTargetRequest addTargetRequest4 = new AddTargetRequest("wdc", "sda", 156);
        addTargetResponse = addTargetService.execute(addTargetRequest1);
        Assert.assertFalse(addTargetResponse.hasErrors());
        addTargetResponse = addTargetService.execute(addTargetRequest2);
        Assert.assertFalse(addTargetResponse.hasErrors());
        addTargetResponse = addTargetService.execute(addTargetRequest3);
        Assert.assertFalse(addTargetResponse.hasErrors());
        addTargetResponse = addTargetService.execute(addTargetRequest4);
        Assert.assertFalse(addTargetResponse.hasErrors());

        ChangeTargetNameRequest changeTargetNameRequest = new ChangeTargetNameRequest(4L, "New name");
        ChangeTargetNameResponse changeTargetNameResponse = changeTargetNameService.execute(changeTargetNameRequest);
        assertNull(changeTargetNameResponse.getErrorList());

        ChangeTargetDescriptionRequest changeTargetDescriptionRequest = new ChangeTargetDescriptionRequest(4L, "New description");
        ChangeTargetDescriptionResponse changeTargetDescriptionResponse = changeTargetDescriptionService.execute(changeTargetDescriptionRequest);
        assertNull(changeTargetDescriptionResponse.getErrorList());

        ChangeTargetDeadlineRequest changeTargetDeadlineRequest = new ChangeTargetDeadlineRequest(4L, 100);
        ChangeTargetDeadlineResponse changeTargetDeadlineResponse = changeTargetDeadlineService.execute(changeTargetDeadlineRequest);
        assertNull(changeTargetDeadlineResponse.getErrorList());

        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("New name");
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "New name");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "New description");
        assertEquals(java.util.Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(100));
    }
}
