package lesson_8.mysql_spring.console;

import lesson_8.mysql_spring.ClientDb;
import lesson_8.mysql_spring.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UpdateRecordConsole {
    @Autowired
    private ClientDb clientDb;
    @Autowired
    private RecordId recordId;
    @Autowired
    private MainConsole mainConsole;

    public void updateRecord() {
        recordId.idMenu();
        Long id = recordId.getRecordId();

        if (!(clientDb.isRecordExist(id))) {
            System.out.println("Record no founded");
            mainConsole.startMainMenu();
        }

        System.out.println("Enter new client's name");
        String userName = new Scanner(System.in).nextLine();
        System.out.println("Enter new client's Surname");
        String userSurname = new Scanner(System.in).nextLine();
        System.out.println("Enter new client's phone");
        String userPhone = new Scanner(System.in).nextLine();
        System.out.println("Enter new client's mail");
        String userMail = new Scanner(System.in).nextLine();

        Client client = new Client();
        client.setName(userName);
        client.setSurname(userSurname);
        client.setPhoneNumber(userPhone);
        client.setEmail(userMail);

        clientDb.updateRecord(id, client);
    }
}