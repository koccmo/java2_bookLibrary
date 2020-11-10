package internet_store.UI.product;

import internet_store.UI.InputCheckUtility;
import internet_store.UI.UIAction;
import internet_store.database.product.ProductDatabase;
import internet_store.services.product.ChangeDescriptionService;

public class ChangeDescriptionUIAction implements UIAction {

    private ChangeDescriptionService changeDescriptionService;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public ChangeDescriptionUIAction(ChangeDescriptionService changeDescriptionService) {
        this.changeDescriptionService = changeDescriptionService;
    }

    public void execute(){
        long id = inputCheckUtility.inputValidLong("Please enter product's id");
        String description = inputCheckUtility.inputValidString("Please enter new description: ");

        if (changeDescriptionService.execute(id, description)) {
            System.out.println("Description of product with id " + id + " was changed");
        }else{
            System.out.println("No id " + id + " in database");
        }
    }

}

