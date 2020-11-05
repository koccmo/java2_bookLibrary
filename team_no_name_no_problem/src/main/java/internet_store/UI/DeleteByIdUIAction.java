package internet_store.UI;

import internet_store.database.ProductDatabase;

class DeleteByIdUIAction implements UIAction {
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    private ProductDatabase productDatabase;

    public DeleteByIdUIAction(ProductDatabase itemDatabase){
        this.productDatabase = itemDatabase;
    }

    public void execute(){

        long id = inputCheckUtility.inputValidLong("Please enter product's id to delete");

        if (productDatabase.deleteById(id)){
            System.out.println("Product is deleted");
        }else{
            System.out.println("No id " + id + " in database");
        }
    }

}

