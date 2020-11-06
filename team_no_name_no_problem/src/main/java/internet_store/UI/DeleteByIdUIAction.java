package internet_store.UI;

import internet_store.database.ProductDatabase;
import internet_store.services.DeleteByIdService;

class DeleteByIdUIAction implements UIAction {
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    private DeleteByIdService deleteByIdService;

    public DeleteByIdUIAction(DeleteByIdService deleteByIdService) {
        this.deleteByIdService = deleteByIdService;
    }

    public void execute(){

        long id = inputCheckUtility.inputValidLong("Please enter product's id to delete");

        if (deleteByIdService.execute(id)){
            System.out.println("Product is deleted");
        }else{
            System.out.println("No id " + id + " in database");
        }
    }

}

