package adventure_time.ui.guides;

import adventure_time.core.services.events.AddEventService;
import adventure_time.ui.UIAction;

public class AddGuideUIAction implements UIAction {

    private final AddEventService addEventService;

    public AddGuideUIAction(AddEventService addEventService) {
        this.addEventService = addEventService;
    }

    @Override
    public void execute() {

    }
}