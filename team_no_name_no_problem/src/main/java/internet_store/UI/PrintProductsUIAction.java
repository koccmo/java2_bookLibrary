package internet_store.UI;

import internet_store.ProductDatabase;

class PrintProductsUIAction implements UIAction {

    private ProductDatabase productDatabase;

    public PrintProductsUIAction(ProductDatabase itemDatabase){
        this.productDatabase = itemDatabase;
    }

    public void execute(){
        productDatabase.printProducts();
    }

}

