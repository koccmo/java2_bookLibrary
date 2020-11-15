package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.product.FindByIdRequest;
import internet_store.core.response.product.FindByIdResponse;
import internet_store.core.services.product.FindProductByIdService;

import java.util.Scanner;

public class FindByIdUIAction implements UIAction {

    private FindProductByIdService findProductByIdService;

    public FindByIdUIAction(FindProductByIdService findProductByIdService) {
        this.findProductByIdService = findProductByIdService;
    }

    public void execute() {

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter id");
        long id = in.nextLong();

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