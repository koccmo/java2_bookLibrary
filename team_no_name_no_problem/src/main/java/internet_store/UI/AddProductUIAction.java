package internet_store.UI;

import internet_store.domain.Product;
import internet_store.services.AddProductServices;

class AddProductUIAction implements UIAction {

    private AddProductServices addProductServices;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public AddProductUIAction(AddProductServices addProductServices) {
        this.addProductServices = addProductServices;
    }

    public void execute(){
        String title = inputCheckUtility.inputValidString("Please enter product's title: ");

        String description = inputCheckUtility.inputValidString("Please enter product's description");

        int price = inputCheckUtility.inputValidInteger("Please enter product's price");

        Product newProduct = new Product(title, description, price);
        addProductServices.addProduct(newProduct);
    }

}

