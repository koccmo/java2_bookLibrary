package ui.events;

import ui.UIAction;

public class ExitEventUIAction implements UIAction {


    @Override
    public void execute() {
        System.out.println("This is the end, my only friend!");
        System.exit(0);
    }
}
