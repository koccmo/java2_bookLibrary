package internet_store.console_ui;

import internet_store.console_ui.UIAction;

public class ExitFromApplicationUIAction implements UIAction {


    @Override
    public void execute() {
        System.exit(0);
    }
}
