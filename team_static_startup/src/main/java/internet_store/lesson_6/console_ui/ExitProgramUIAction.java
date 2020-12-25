package internet_store.lesson_6.console_ui;

import org.springframework.stereotype.Component;

@Component
public class ExitProgramUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("Good by!");
        System.exit(0);
    }

}
