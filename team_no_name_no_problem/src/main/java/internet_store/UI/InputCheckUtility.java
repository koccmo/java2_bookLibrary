package internet_store.UI;

import java.util.Scanner;

public class InputCheckUtility {

    public String inputValidString(String message){
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

    public int inputValidInteger(String message){
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

            if (input >= 0){
                break;
            }else{
                System.out.println("Please enter valid value!");
            }
        }
        return input;
    }

    public long inputValidLong(String message){
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
