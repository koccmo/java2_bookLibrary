package internet_store.console_ui.customer;

import internet_store.MainMenuApplication;
import internet_store.console_ui.UIAction;

public class ExitUIAction implements UIAction {

    @Override
    public void execute(){
            MainMenuApplication mainMenuApplication = new MainMenuApplication();
            mainMenuApplication.execute();
    }
}
