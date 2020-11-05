package internet_store.UI;

import internet_store.database.ProductDatabase;

class ChangeDescriptionUIAction implements UIAction {

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

