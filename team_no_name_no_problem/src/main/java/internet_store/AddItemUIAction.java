package internet_store;

import java.util.Scanner;

class AddItemUIAction implements UIAction {

    private ProductDatabase productDatabase;

    public AddItemUIAction(ProductDatabase itemDatabase){
        this.productDatabase = itemDatabase;
    }

    public void execute(){
        String title = inputValidString("Please enter product's title: ");

        String description = inputValidString("Please enter product's description");

        int price = inputValidInteger("Please enter product's price");

        Product newProduct = new Product(title, description, price);
        productDatabase.save(newProduct);
    }

    private String inputValidString(String message){
        String input;
        Scanner in = new Scanner(System.in);
        while (true){
            System.out.println(message);
            input = in.nextLine();
            if (input != null && !input.isEmpty()){
                break;
            }else{
                System.out.println("Please enter valid value!");
            }
        }
        return input;
    }

    private int inputValidInteger(String message){
        int input;
        Scanner in = new Scanner(System.in);
        while (true){
            System.out.println(message);
            while (true){
                try {
                    input = Integer.parseInt(in.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("It's not valid number! Please input valid number!");
                }
            }

            if (input > 0){
                break;
            }else{
                System.out.println("Please enter valid value!");
            }
        }
        return input;
    }

}

