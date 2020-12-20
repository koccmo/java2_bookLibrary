package team_VK.application.ui;

import team_VK.application.database.DIComponent;

@DIComponent
public class ExitProgramUIAction implements UIActions {

    @Override
    public void execute() {

        System.out.println("Good by!");
        System.exit(0);

    }
}
