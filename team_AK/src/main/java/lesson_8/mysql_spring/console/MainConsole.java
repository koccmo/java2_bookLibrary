package lesson_8.mysql_spring.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainConsole {
    @Autowired
    private MainMenu mainMenu;
    @Autowired
    private InsertRecordConsole insertRecord;
    @Autowired
    private PrintRecordsConsole printRecords;
    @Autowired
    private DeleteRecordConsole deleteRecord;
    @Autowired
    private UpdateRecordConsole updateRecord;

    public void startMainMenu() {
        do {
            mainMenu.showMenu();
            int userInput = mainMenu.getUserInput();
            switch (userInput) {
                case 1 -> insertRecord.insertRecord();
                case 2 -> updateRecord.updateRecord();
                case 3 -> deleteRecord.deleteRecord();
                case 4 -> printRecords.printRecords();
                case 0 -> System.exit(0);
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (true);
    }
}