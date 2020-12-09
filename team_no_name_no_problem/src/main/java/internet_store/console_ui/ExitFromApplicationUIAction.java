package internet_store.console_ui;

import internet_store.dependency_injection.DIComponent;

@DIComponent
public class ExitFromApplicationUIAction implements UIAction {


    @Override
    public void execute() {
        System.exit(0);
    }
}
