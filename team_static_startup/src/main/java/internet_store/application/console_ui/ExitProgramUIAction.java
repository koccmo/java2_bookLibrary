package internet_store.application.console_ui;

import internet_store.application.dependency_injection.DIComponent;

@DIComponent
public class ExitProgramUIAction implements UIAction{

    @Override
    public void execute() {
        System.out.println("Good by!");
        System.exit(0);
    }

}
