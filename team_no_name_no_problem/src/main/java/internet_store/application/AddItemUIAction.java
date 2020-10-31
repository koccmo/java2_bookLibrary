package internet_store.application;

import internet_store.Product;
import internet_store.ProductDatabase;

import java.util.Scanner;

class AddItemUIAction implements UIAction {

    private ProductDatabase productDatabase;

    public AddItemUIAction(ProductDatabase itemDatabase){
        this.productDatabase = itemDatabase;
    }

    public void execute(){
        System.out.println("Please enter product's title: ");
        Scanner in = new Scanner(System.in);
        String title = in.nextLine();

        System.out.println("Please enter product's description");
        String description = in.nextLine();

        int price = 0;
        while (true){
            try {
                System.out.println("Please enter product's price");
                price = Integer.parseInt(in.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("It's not valid number!");
            }
        }


        Product newProduct = new Product(title, description, price);
        productDatabase.save(newProduct);
    }

}

