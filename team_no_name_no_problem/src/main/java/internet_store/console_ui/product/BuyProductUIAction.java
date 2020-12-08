package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.product.BuyProductRequest;
import internet_store.core.response.product.BuyProductResponse;
import internet_store.core.services.product.BuyProductService;

import java.util.Scanner;

public class BuyProductUIAction implements UIAction {

    private BuyProductService buyProductService;

    public BuyProductUIAction(BuyProductService buyProductService) {
        this.buyProductService = buyProductService;
    }

    @Override
    public void execute() {

            Scanner in = new Scanner(System.in);

            System.out.println("To add product in Shopping Cart, please enter ID of this product: ");
            Long id = in.nextLong();

            System.out.println("Please enter quantity of this product");
            Integer quantity = in.nextInt();

            BuyProductRequest buyProductRequest = new BuyProductRequest(id, quantity);
            BuyProductResponse buyProductResponse = buyProductService.execute(buyProductRequest);
    }
}
