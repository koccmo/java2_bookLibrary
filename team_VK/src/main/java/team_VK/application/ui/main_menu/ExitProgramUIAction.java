package team_VK.application.ui.main_menu;

import org.springframework.stereotype.Component;
import team_VK.application.ui.UIActions;

@Component
public class ExitProgramUIAction implements UIActions {

    @Override
    public void execute() {

        System.out.println("Good by!");
        System.exit(0);

    }
}
