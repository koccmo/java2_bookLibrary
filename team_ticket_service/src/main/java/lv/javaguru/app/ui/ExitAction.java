package lv.javaguru.app.ui;

public class ExitAction implements UIActions {

    @Override
    public void execute() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
