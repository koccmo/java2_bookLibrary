package adventure_time.core.services.events;

//import adventure_time.dependencies.DIComponent;
//import adventure_time.dependencies.DIDependency;
import adventure_time.core.domain.Events;
import adventure_time.database.events.EventDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@DIComponent
@Component
public class DisplayEventListService {
//    @DIDependency
@Autowired
private EventDatabase databaseEvents;

//    public DisplayEventListService(EventDatabase databaseEvents) {
//        this.databaseEvents = databaseEvents;
//    }

    public List<Events> getEventsList () {
        return databaseEvents.getEventsList();
    }

}
