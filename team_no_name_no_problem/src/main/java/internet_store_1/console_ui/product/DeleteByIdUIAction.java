package internet_store_1.console_ui.product;

import internet_store_1.console_ui.UIAction;
import internet_store_1.core.requests.product.DeleteProductRequest;
import internet_store_1.core.response.product.DeleteProductResponse;
import internet_store_1.core.services.product.DeleteByIdService;

import java.util.Scanner;

public class DeleteByIdUIAction implements UIAction {

    private DeleteByIdService deleteByIdService;

    public DeleteByIdUIAction(DeleteByIdService deleteByIdService) {
        this.deleteByIdService = deleteByIdService;
    }

    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter product's id to delete");
        long id = in.nextLong();

        DeleteProductRequest deleteProductRequest = new DeleteProductRequest(id);
        DeleteProductResponse deleteProductResponse = deleteByIdService.execute(deleteProductRequest);

        if (deleteProductResponse.hasErrors()){
            deleteProductResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Product is deleted");
        }
    }

}

