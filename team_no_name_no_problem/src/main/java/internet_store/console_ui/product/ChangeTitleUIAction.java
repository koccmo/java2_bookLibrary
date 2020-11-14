package internet_store.console_ui.product;

import internet_store.console_ui.InputCheckUtility;
import internet_store.console_ui.UIAction;
import internet_store.core.services.product.ChangeTitleService;

public class ChangeTitleUIAction implements UIAction {

    private ChangeTitleService changeTitle;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public ChangeTitleUIAction(ChangeTitleService changeTitle) {
        this.changeTitle = changeTitle;
    }

    public void execute(){
        long id = inputCheckUtility.inputValidLong("Please enter product's id");
        String title = inputCheckUtility.inputValidString("Please enter new title: ");

        if (changeTitle.execute(id, title)) {
            System.out.println("Title of product with id " + id + " was changed");
        }else{
            System.out.println("No id " + id + " in database");
        }
    }

}

