package internet_store.lesson_2.ui;

public class ExitProgramUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("Good by!");
        System.exit(0);
    }

}
