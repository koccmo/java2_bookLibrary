package internet_store.console_ui.product;

import internet_store.MainMenuApplication;
import internet_store.console_ui.UIAction;

public class ExitToMainMenuUIAction implements UIAction {

    @Override
    public void execute() {
        MainMenuApplication mainMenuApplication = new MainMenuApplication();
        mainMenuApplication.execute();


    }

}
