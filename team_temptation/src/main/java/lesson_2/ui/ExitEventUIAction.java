package lesson_2.ui;

public class ExitEventUIAction implements UIAction {


    @Override
    public void execute() {
        System.out.println("Good by!");
        System.exit(0);
    }
}
