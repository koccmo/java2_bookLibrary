package adventure_time.ui.events;

import adventure_time.database.events.EventDatabase;
import adventure_time.core.domain.Events;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.Integer.parseInt;

//@DIComponent
@Component
public class StartUpEventUIAction implements UIAction {

//    @DIDependency
    @Autowired
    private EventDatabase databaseEvents;

//    public StartUpEventUIAction(EventDatabase databaseEvents) {
//        this.databaseEvents = databaseEvents;
//    }

    @Override
    public void execute() {
        Events event = new Events("Bike trip to Jurmala-12", "bike trip",
                12, 10, 2, "Riga Jurmala",
                "Bike trip. Lunch. Dinner");
        databaseEvents.add(event);
        event = new Events("Bike trip to Jurmala-16", "bike trip",
                16, 10, 2, "Riga Jurmala",
                "Bike trip. Lunch. Dinner");
        databaseEvents.add(event);
        event = new Events("Bike trip to Jurmala-8", "bike trip",
                8, 10, 2, "Riga Jurmala",
                "Bike trip. Lunch. Dinner");
        databaseEvents.add(event);
        event = new Events("Bus trip to Sigulga and Cesis", "bus trip",
                12, 25, 12, "Riga Sigulda Cesis",
                "Bus trip. Lunch. Dinner");
        databaseEvents.add(event);
        event = new Events("Bus trip to Bauska", "bus trip",
                12, 25, 12, "Riga Bauska",
                "Bus trip to Bauska. Bauska castle. Lunch. Dinner");
        databaseEvents.add(event);
        event = new Events("Motorcycle trip to the forest", "motorcycle trip",
                8, 4, 4, "Riga Saulkrasti",
                "Motorcycle trip. Dinner");
        databaseEvents.add(event);
        event = new Events("Motorcycle trip to Jurmala", "motorcycle trip",
                8, 4, 4, "Riga Jurmala",
                "Motorcycle trip. Dinner");
        databaseEvents.add(event);
        event = new Events("Motorcycle trip to Ventspils", "motorcycle trip",
                16, 8, 4, "Riga Ventspils",
                "Motorcycle trip to Ventspils. Lunch. Dinner");
        databaseEvents.add(event);
        event = new Events("Walking trip to the Riga center", "walking trip",
                4, 10, 6, "Riga centre",
                "Walking trip to the Riga center. Brunch");
        databaseEvents.add(event);
        event = new Events("Walking trip to the Daugavgrivas Cietoksnis", "walking trip",
                4, 15, 6, "Riga centre Daugavgriva",
                "Walking trip to the Daugavgrivas Cietoksnis. Brunch");
        databaseEvents.add(event);
        event = new Events("Bike trip to Salaspils", "bike trip",
                10, 10, 6, "Riga Salaspils",
                "Bike trip to Salaspils. Brunch. Dinner");
        databaseEvents.add(event);
        event = new Events("Bike trip to Riga", "bike trip",
                10, 10, 6, "Daugavpils Riga",
                "Bike trip to Riga. Brunch. Dinner");
        databaseEvents.add(event);
    }
}