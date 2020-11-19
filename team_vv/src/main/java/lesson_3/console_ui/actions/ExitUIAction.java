package lesson_3.console_ui.actions;

import lesson_3.console_ui.UIAction;

public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.exit(0);
    }
}
