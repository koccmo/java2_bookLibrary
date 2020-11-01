package internet_store;

import java.util.Scanner;

class DeleteByIdUIAction implements UIAction {

    private ProductDatabase productDatabase;

    public DeleteByIdUIAction(ProductDatabase itemDatabase){
        this.productDatabase = itemDatabase;
    }

    public void execute(){

        long id = inputValidLong("Please enter product's id to delete");

        if (productDatabase.deleteById(id)){
            System.out.println("Product is deleted");
        }else{
            System.out.println("No id " + id + " in database");
        }
    }

    private long inputValidLong(String message){
        long input;
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

