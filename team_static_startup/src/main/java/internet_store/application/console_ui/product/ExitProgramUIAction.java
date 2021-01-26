package internet_store.application.console_ui.product;

import internet_store.application.console_ui.UIAction;
import org.springframework.stereotype.Component;

@Component
public class ExitProgramUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("Good by!");
        System.exit(0);
    }

}
