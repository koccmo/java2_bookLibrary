package internet_store;

import java.util.Scanner;

class PrintProductsUIAction implements UIAction {

    private ProductDatabase productDatabase;

    public PrintProductsUIAction(ProductDatabase itemDatabase){
        this.productDatabase = itemDatabase;
    }

    public void execute(){
        productDatabase.printProducts();
    }

}

