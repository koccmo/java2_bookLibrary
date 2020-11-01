package internet_store;

import java.util.Scanner;

class ChangeDescriptionUIAction implements UIAction {

    private ProductDatabase productDatabase;

    public ChangeDescriptionUIAction(ProductDatabase itemDatabase){
        this.productDatabase = itemDatabase;
    }

    public void execute(){
        long id = inputValidLong("Please enter product's id");
        String description = inputValidString("Please enter new description: ");

        if (productDatabase.changeDescription(id, description)) {
            System.out.println("Description of product with id " + id + " was changed");
        };
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

