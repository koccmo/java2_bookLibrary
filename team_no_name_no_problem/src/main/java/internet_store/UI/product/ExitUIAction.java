package internet_store.UI.product;

import internet_store.UI.UIAction;

public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println(":) End of work day!");
        System.exit(0);
    }

}
