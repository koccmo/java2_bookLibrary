package lesson_5.ui;

public class ExitUICommand implements UICommand {

    @Override
    public void execute() {
        System.out.println("Thank Your! Good by!");
        System.exit(0);
    }
}
