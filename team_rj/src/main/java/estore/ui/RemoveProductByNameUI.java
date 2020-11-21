package estore.ui;

import estore.core.requests.RemoveProductByNameRequest;
import estore.core.responses.RemoveProductByNameResponse;
import estore.core.service.RemoveProductByNameService;

import java.util.Scanner;

public class RemoveProductByNameUI implements UIAction {

    private RemoveProductByNameService removeProductByNameService;

    public RemoveProductByNameUI(RemoveProductByNameService removeProductByNameService) {
        this.removeProductByNameService = removeProductByNameService;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of product to remove: ");
        String productToRemove = sc.nextLine();

        RemoveProductByNameRequest request = new RemoveProductByNameRequest(productToRemove);
        RemoveProductByNameResponse response = removeProductByNameService.execute(request);

        if (response.getProductsRemoved() == -1) {
            for (int i = 0; i < response.getErrors().size(); i++) {
                System.out.print(response.getErrors().get(i).getField() + " ");
                System.out.println(response.getErrors().get(i).getMessage());
            }
        } else {
            if (response.getProductsRemoved() == 1) {
                System.out.println(response.getProductsRemoved() + " product removed.");
            } else {
                System.out.println(response.getProductsRemoved() + " products removed.");
            }
        }
    }
}
