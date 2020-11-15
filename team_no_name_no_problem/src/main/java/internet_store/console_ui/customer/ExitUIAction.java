package internet_store.console_ui.customer;

import internet_store.console_ui.UIAction;

public class ExitUIAction implements UIAction {

    @Override
    public void execute(){
        System.out.println("Bye - bye!");
        System.exit(0);
    }
}
