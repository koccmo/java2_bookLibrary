package team_VK.application.ui;

public class ExitProgramUIAction implements UIActions {

    @Override
    public void execute() {

        System.out.println("Good by!");
        System.exit(0);

    }
}
