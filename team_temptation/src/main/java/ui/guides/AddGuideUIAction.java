package ui.guides;

import core.services.events.AddEventService;
import ui.UIAction;

public class AddGuideUIAction implements UIAction {

    private final AddEventService addEventService;

    public AddGuideUIAction(AddEventService addEventService) {
        this.addEventService = addEventService;
    }

    @Override
    public void execute() {

    }
}