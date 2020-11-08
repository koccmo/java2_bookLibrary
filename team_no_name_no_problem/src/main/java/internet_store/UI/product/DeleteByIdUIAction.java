package internet_store.UI.product;

import internet_store.UI.InputCheckUtility;
import internet_store.UI.UIAction;
import internet_store.services.product.DeleteByIdService;

public class DeleteByIdUIAction implements UIAction {
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

