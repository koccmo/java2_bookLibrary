package java2.application_target_list.core.acceptancetests;

import java2.application_target_list.core.requests.*;
import java2.application_target_list.core.services.*;
import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.responses.ChangeTargetDeadlineResponse;
import java2.application_target_list.core.responses.ChangeTargetDescriptionResponse;
import java2.application_target_list.core.responses.ChangeTargetNameResponse;
import java2.application_target_list.core.responses.SearchTargetByNameResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AcceptanceTests {


    private ApplicationContext applicationContext;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
    }

    @Test
    public void test() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name1", "description1", 1);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 4);
        AddTargetRequest addTargetRequest3 = new AddTargetRequest("name3", "description3", 6);
        AddTargetRequest addTargetRequest4 = new AddTargetRequest("wdc", "sda", 156);
        AddTargetService addTargetService = applicationContext.getBean(AddTargetService.class);
        addTargetService.execute(addTargetRequest1);
        addTargetService.execute(addTargetRequest2);
        addTargetService.execute(addTargetRequest3);
        addTargetService.execute(addTargetRequest4);

        ChangeTargetNameService changeTargetNameService = applicationContext.getBean(ChangeTargetNameService.class);
        ChangeTargetNameRequest changeTargetNameRequest = new ChangeTargetNameRequest(4L, "New name");
        ChangeTargetNameResponse changeTargetNameResponse = changeTargetNameService.execute(changeTargetNameRequest);
        assertNull(changeTargetNameResponse.getErrorList());

        ChangeTargetDescriptionService changeTargetDescriptionService = applicationContext.getBean(ChangeTargetDescriptionService.class);
        ChangeTargetDescriptionRequest changeTargetDescriptionRequest = new ChangeTargetDescriptionRequest(4L, "New description");
        ChangeTargetDescriptionResponse changeTargetDescriptionResponse = changeTargetDescriptionService.execute(changeTargetDescriptionRequest);
        assertNull(changeTargetDescriptionResponse.getErrorList());

        ChangeTargetDeadlineService changeTargetDeadlineService = applicationContext.getBean(ChangeTargetDeadlineService.class);
        ChangeTargetDeadlineRequest changeTargetDeadlineRequest = new ChangeTargetDeadlineRequest(4L, 100);
        ChangeTargetDeadlineResponse changeTargetDeadlineResponse = changeTargetDeadlineService.execute(changeTargetDeadlineRequest);
        assertNull(changeTargetDeadlineResponse.getErrorList());

        SearchTargetByNameService searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("New name");
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);
        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "New name");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "New description");
        assertEquals(java.util.Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(100));

    }
}
