package internet_store.UI.product;

import internet_store.UI.InputCheckUtility;
import internet_store.UI.UIAction;
import internet_store.database.product.ProductDatabase;
import internet_store.services.product.ChangeTitleService;

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

