package internet_store.UI;

import internet_store.domain.Product;
import internet_store.ProductDatabase;

class AddItemUIAction implements UIAction {

    private ProductDatabase productDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public AddItemUIAction(ProductDatabase itemDatabase){
        this.productDatabase = itemDatabase;
    }

    public void execute(){
        String title = inputCheckUtility.inputValidString("Please enter product's title: ");

        String description = inputCheckUtility.inputValidString("Please enter product's description");

        int price = inputCheckUtility.inputValidInteger("Please enter product's price");

        Product newProduct = new Product(title, description, price);
        productDatabase.save(newProduct);
    }

}

