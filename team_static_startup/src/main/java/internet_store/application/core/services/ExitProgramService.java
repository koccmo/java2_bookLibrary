package internet_store.application.core.services;

import internet_store.application.console_ui.UIAction;
import org.springframework.stereotype.Component;

@Component
public class ExitProgramService implements UIAction {

    @Override
    public void execute() {
        System.out.println("Good by!");
        System.exit(0);
    }

}
