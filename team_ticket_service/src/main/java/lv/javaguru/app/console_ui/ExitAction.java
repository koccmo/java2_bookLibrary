package lv.javaguru.app.console_ui;

import org.springframework.stereotype.Component;

@Component
public class ExitAction implements UIActions {

    @Override
    public void execute() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
