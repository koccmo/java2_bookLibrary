package internet_store.UI.customer;

import internet_store.UI.UIAction;

public class ExitUIAction implements UIAction {

    @Override
    public void execute(){
        System.out.println("Bye - bye!");
        System.exit(0);
    }
}
