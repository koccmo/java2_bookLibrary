package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.product.FindByIdRequest;
import internet_store.core.response.product.FindByIdResponse;
import internet_store.core.services.product.FindProductByIdService;
import internet_store.dependency_injection.DIComponent;
import internet_store.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class FindByIdUIAction implements UIAction {

    @DIDependency private FindProductByIdService findProductByIdService;

    @Override
    public void execute() {

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter id");
        Long id = in.nextLong();

        FindByIdRequest findByIdRequest = new FindByIdRequest(id);
        FindByIdResponse findByIdResponse = findProductByIdService.execute(findByIdRequest);

        if (findByIdResponse.hasErrors()){
            findByIdResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Product with id " + id);
            System.out.println(findByIdResponse.getProduct());
        }
    }
}