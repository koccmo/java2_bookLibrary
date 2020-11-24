package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.product.ChangeProductRequest;
import internet_store.core.response.product.ChangeProductResponse;
import internet_store.core.services.product.ChangeProductService;

import java.util.Scanner;

public class ChangeProductUIAction implements UIAction {

    private final ChangeProductService changeProductService;

    public ChangeProductUIAction(ChangeProductService changeProductService) {
        this.changeProductService = changeProductService;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter new id for product");
        Long id = in.nextLong();

        System.out.println("Please enter new title for product");
        String title = in.nextLine();
        title = in.nextLine();

        System.out.println("Please enter new description for product");
        String description = in.nextLine();

        System.out.println("Please enter new price for product");
        Integer price = in.nextInt();

        ChangeProductRequest changeProductRequest = new ChangeProductRequest(id, title, description, price);
        ChangeProductResponse changeProductResponse = changeProductService.execute(changeProductRequest);

        if (changeProductResponse.hasErrors()){
            changeProductResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Product is updated");
        }

    }
}
