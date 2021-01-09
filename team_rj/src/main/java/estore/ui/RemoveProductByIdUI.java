package estore.ui;

import estore.core.requests.RemoveProductByIdRequest;
import estore.core.responses.RemoveProductByIdResponse;
import estore.core.service.RemoveProductByIdService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveProductByIdUI implements UIAction {

    private RemoveProductByIdService removeProductByIdService;

    public RemoveProductByIdUI(RemoveProductByIdService removeProductByIdService) {
        this.removeProductByIdService = removeProductByIdService;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id of product to remove: ");
        String productToRemoveId = sc.nextLine();

        RemoveProductByIdRequest request = new RemoveProductByIdRequest(productToRemoveId);
        RemoveProductByIdResponse response = removeProductByIdService.execute(request);

        if (response.getProductsRemoved() == -1) {
            for (int i = 0; i < response.getErrors().size(); i++) {
                System.out.print("ERROR! ");
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
