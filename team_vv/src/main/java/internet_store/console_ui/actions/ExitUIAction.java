package internet_store.console_ui.actions;

import internet_store.console_ui.UIAction;

public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.exit(0);
    }
}
