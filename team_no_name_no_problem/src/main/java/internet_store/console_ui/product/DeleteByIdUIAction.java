package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.product.DeleteProductRequest;
import internet_store.core.response.product.DeleteProductResponse;
import internet_store.core.services.product.DeleteByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component public class DeleteByIdUIAction implements UIAction {

    @Autowired private DeleteByIdService deleteByIdService;

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

