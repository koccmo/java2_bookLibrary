package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.product.FindProductByIdRequest;
import internet_store.core.response.product.FindProductByIdResponse;
import internet_store.core.services.product.FindProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindByIdUIAction implements UIAction {

    @Autowired private FindProductByIdService findProductByIdService;

    @Override
    public void execute() {

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter id");
        Long id = in.nextLong();

        FindProductByIdRequest findByIdRequest = new FindProductByIdRequest(id);
        FindProductByIdResponse findByIdResponse = findProductByIdService.execute(findByIdRequest);

        if (findByIdResponse.hasErrors()){
            findByIdResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Product with id " + id);
            System.out.println(findByIdResponse.getProducts());
        }
    }
}