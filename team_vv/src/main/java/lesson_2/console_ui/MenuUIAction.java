package lesson_2.console_ui;

public class MenuUIAction implements UIAction {



    @Override
    public void execute() {
        System.out.println("Actions: ");
        System.out.println("[1] Show targets list");
        System.out.println("[2] Add target");
        System.out.println("[3] Delete target");
        System.out.println("[4] Change target parameters");
        System.out.println("[0] Quit");
    }
}
