package lesson_8.mysql_spring.console;

import lesson_8.mysql_spring.ClientDb;
import lesson_8.mysql_spring.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrintRecordsConsole {
    @Autowired
    private ClientDb clientDb;

    public void printRecords() {
        System.out.println(" [ All records in DB ]");

        List<Client> clientsFromDb = clientDb.findAllRecords();
        if (clientsFromDb.size() != 0) {
            clientsFromDb.forEach(c -> System.out.println(c.toString()));
        } else {
            System.out.println("No records");
        }
        System.out.println("------------------------------------");
    }
}