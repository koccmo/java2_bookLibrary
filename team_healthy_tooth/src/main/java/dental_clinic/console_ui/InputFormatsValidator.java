package dental_clinic.console_ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputFormatsValidator {

    public int inputInteger(String message){
        int input;
        Scanner in = new Scanner(System.in);
        System.out.println(message);
            while (true){
                try {
                    input = Integer.parseInt(in.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("It's not valid number! Please input valid number!");
                }
            }
        return input;
    }

    public long inputLong(String message){
        Long input;
        Scanner in = new Scanner(System.in);
        System.out.println(message);
            while (true){
                try {
                    input = Long.parseLong(in.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("It's not valid number! Please input valid number!");
                }
            }
        return input;
    }
}
