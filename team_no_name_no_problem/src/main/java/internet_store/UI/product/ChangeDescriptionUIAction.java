package internet_store.UI.product;

import internet_store.UI.InputCheckUtility;
import internet_store.UI.UIAction;
import internet_store.database.product.ProductDatabase;

public class ChangeDescriptionUIAction implements UIAction {

    private ProductDatabase productDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public ChangeDescriptionUIAction(ProductDatabase itemDatabase){
        this.productDatabase = itemDatabase;
    }

    public void execute(){
        long id = inputCheckUtility.inputValidLong("Please enter product's id");
        String description = inputCheckUtility.inputValidString("Please enter new description: ");

        if (productDatabase.changeDescription(id, description)) {
            System.out.println("Description of product with id " + id + " was changed");
        }
    }

}

