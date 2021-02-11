package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.product.DeleteProductByOtherRequest;
import internet_store.core.response.product.DeleteByOtherResponse;
import internet_store.core.services.product.DeleteByOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteByOtherUIAction implements UIAction {

    @Autowired
    private DeleteByOtherService deleteByOtherService;

    @Override
    public void execute (){

        Scanner in = new Scanner(System.in);

        System.out.println("Please select criteria by which you wish to delete product/products from existing database");
        System.out.println("Criterias are: title, description and price range.");
        System.out.println("Each criteria may be used individually or in combination with others.");
        System.out.println("If a certain criteria is not needed, please press enter to skip and move to the next criteria.");
        System.out.println("Price range can be skipped only by entering zeros.");
        System.out.println();

        System.out.println("Please enter title to delete by title");
        String title = in.nextLine();

        System.out.println("Please enter description to delete by description");
        String description = in.nextLine();

        System.out.println("Please enter start price to delete by start price");
        Integer startPrice = in.nextInt();

        System.out.println("Please enter end price to delete by end price");
        Integer endPrice = in.nextInt();
        in.nextLine();

        DeleteProductByOtherRequest deleteProductByOtherRequest = new DeleteProductByOtherRequest(title, description, startPrice,
                endPrice);
        DeleteByOtherResponse deleteByOtherResponse = (DeleteByOtherResponse) deleteByOtherService.execute(deleteProductByOtherRequest);

        if (deleteByOtherResponse.hasErrors()){
            deleteByOtherResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Product is deleted");
        }
    }
}
