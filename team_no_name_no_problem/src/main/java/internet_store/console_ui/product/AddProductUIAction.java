package internet_store.console_ui.product;

import internet_store.console_ui.InputCheckUtility;
import internet_store.console_ui.UIAction;
import internet_store.core.domain.Product;
import internet_store.core.services.product.AddProductService;

public class AddProductUIAction implements UIAction {

    private AddProductService addProductService;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public AddProductUIAction(AddProductService addProductService) {
        this.addProductService = addProductService;
    }

    public void execute(){
        String title = inputCheckUtility.inputValidString("Please enter product's title: ");

        String description = inputCheckUtility.inputValidString("Please enter product's description");

        int price = inputCheckUtility.inputValidInteger("Please enter product's price");

        Product newProduct = new Product(title, description, price);
        addProductService.execute(newProduct);
    }

}

