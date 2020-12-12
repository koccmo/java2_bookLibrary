package internet_store.console_ui;

import org.springframework.stereotype.Component;

@Component
public class ExitFromApplicationUIAction implements UIAction {


    @Override
    public void execute() {
        System.exit(0);
    }
}
