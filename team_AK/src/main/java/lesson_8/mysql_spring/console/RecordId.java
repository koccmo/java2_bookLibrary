package lesson_8.mysql_spring.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class RecordId {
    @Autowired
    private MainConsole mainConsole;
    private Long recordId;

    public void idMenu() {
        System.out.println("Enter record ID");
        try {
            recordId = new Scanner(System.in).nextLong();
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input");
            mainConsole.startMainMenu();
        }
    }

    public Long getRecordId() {
        return recordId;
    }
}