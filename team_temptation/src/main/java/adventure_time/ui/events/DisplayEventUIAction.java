package adventure_time.ui.events;

import adventure_time.core.services.events.DisplayEventListService;
import adventure_time.dependencies.DIComponent;
import adventure_time.dependencies.DIDependency;

import adventure_time.domain.Events;
import adventure_time.ui.UIAction;

@DIComponent
public class DisplayEventUIAction implements UIAction {

    @DIDependency
    private DisplayEventListService displayEventListService;

//    public DisplayEventUIAction(DisplayEventListService displayEventListService) {
//        this.displayEventListService = displayEventListService;
//    }

    @Override
    public void execute() {
        System.out.println("Here is a list of events: ");
        for (Events item : displayEventListService.getEventsList()) {
            System.out.println(item);
        }
        System.out.println("This is the end.");
    }
}
