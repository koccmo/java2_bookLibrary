package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;

public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println(":) End of work day!");
        System.exit(0);
    }

}
