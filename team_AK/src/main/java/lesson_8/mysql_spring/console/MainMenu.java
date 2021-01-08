package lesson_8.mysql_spring.console;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class MainMenu {
    final int INCORRECT_USER_INPUT = -1;
    @Getter
    private int userInput;

    public void showMenu() {
        System.out.println("[ Demo CRUD operations with Spring and MySQL ]");
        System.out.println("1 - Insert record");
        System.out.println("2 - Update record");
        System.out.println("3 - Delete record");
        System.out.println("4 - Show all records");
        System.out.println("0 - Exit");
        try {
            userInput = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            userInput = INCORRECT_USER_INPUT;
        }
    }
}