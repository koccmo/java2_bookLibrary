package team_VK.application.ui.main_menu;

import team_VK.application.database.DIComponent;
import team_VK.application.ui.UIActions;

@DIComponent
public class ExitProgramUIAction implements UIActions {

    @Override
    public void execute() {

        System.out.println("Good by!");
        System.exit(0);

    }
}
