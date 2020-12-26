package internet_store.console_ui.ProgramMenu;

import internet_store.console_ui.UIAction;
import org.springframework.stereotype.Component;

@Component public class ExitFromApplicationUIAction implements UIAction {


    @Override
    public void execute() {
        System.exit(0);
    }
}
