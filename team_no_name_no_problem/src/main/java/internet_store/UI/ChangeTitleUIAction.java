package internet_store.UI;

import internet_store.ProductDatabase;

class ChangeTitleUIAction implements UIAction {

    private ProductDatabase productDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public ChangeTitleUIAction(ProductDatabase itemDatabase){
        this.productDatabase = itemDatabase;
    }

    public void execute(){
        long id = inputCheckUtility.inputValidLong("Please enter product's id");
        String title = inputCheckUtility.inputValidString("Please enter new title: ");

        if (productDatabase.changeTitle(id, title)) {
            System.out.println("Title of product with id " + id + " was changed");
        }else{
            System.out.println("No id " + id + " in database");
        }
    }

}

