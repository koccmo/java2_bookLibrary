package adventure_time.core.services.events;

import adventure_time.dependencies.DIComponent;
import adventure_time.dependencies.DIDependency;
import adventure_time.domain.Events;
import adventure_time.database.events.EventDatabase;

import java.util.List;

@DIComponent
public class DisplayEventListService {
    @DIDependency
    private EventDatabase databaseEvents;

//    public DisplayEventListService(EventDatabase databaseEvents) {
//        this.databaseEvents = databaseEvents;
//    }

    public List<Events> getEventsList () {
        return databaseEvents.getEventsList();
    }

}
