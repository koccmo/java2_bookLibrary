package application_target_list.console_ui.actions;

import application_target_list.console_ui.UIAction;

public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.exit(0);
    }
}
