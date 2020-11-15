package online_schedule.ui;

import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputUtility {

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

    public String inputValidMobileNumber(String message){
        String input;
        Scanner in = new Scanner(System.in);
        while (true){
            System.out.println(message);
            input = in.nextLine();
            if (containsOnlyDigits(input) && input.length() == 8){
                break;
            }else{
                System.out.println("Please enter valid value!");
            }
        }
        return input;
    }



    private boolean containsOnlyDigits(String input){
        String regex = "[0-9]+";

        Pattern p = Pattern.compile(regex);
        if (input == null) {
            return false;
        }

        Matcher m = p.matcher(input);

        return m.matches();
    }
}
