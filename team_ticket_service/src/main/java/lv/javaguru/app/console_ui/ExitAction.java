package lv.javaguru.app.console_ui;

import lv.javaguru.app.dependency_injection.DIComponent;

@DIComponent
public class ExitAction implements UIActions {

    @Override
    public void execute() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
