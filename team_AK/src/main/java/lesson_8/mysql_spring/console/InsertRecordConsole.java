package lesson_8.mysql_spring.console;

import lesson_8.mysql_spring.ClientDb;
import lesson_8.mysql_spring.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InsertRecordConsole {
    @Autowired
    private ClientDb clientDb;

    public void insertRecord() {
        System.out.println("Enter client's name");
        String userName = new Scanner(System.in).nextLine();
        System.out.println("Enter client's Surname");
        String userSurname = new Scanner(System.in).nextLine();
        System.out.println("Enter client's phone");
        String userPhone = new Scanner(System.in).nextLine();
        System.out.println("Enter client's mail");
        String userMail = new Scanner(System.in).nextLine();

        Client client = new Client();
        client.setName(userName);
        client.setSurname(userSurname);
        client.setPhoneNumber(userPhone);
        client.setEmail(userMail);

        clientDb.insertRecord(client);

        Client clientInDb = clientDb.findByName(userName);
        System.out.println("Information about record in DB");
        System.out.println(clientInDb.toString());
        System.out.println();
    }
}