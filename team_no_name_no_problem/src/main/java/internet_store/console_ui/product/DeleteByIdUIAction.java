package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.product.DeleteProductRequest;
import internet_store.core.response.product.DeleteProductResponse;
import internet_store.core.services.product.DeleteByIdService;
import internet_store.dependency_injection.DIComponent;
import internet_store.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class DeleteByIdUIAction implements UIAction {

    @DIDependency private DeleteByIdService deleteByIdService;

    @Override
    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter product's id to delete");
        Long id = in.nextLong();

        DeleteProductRequest deleteProductRequest = new DeleteProductRequest(id);
        DeleteProductResponse deleteProductResponse = deleteByIdService.execute(deleteProductRequest);

        if (deleteProductResponse.hasErrors()){
            deleteProductResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Product is deleted");
        }
    }

}

