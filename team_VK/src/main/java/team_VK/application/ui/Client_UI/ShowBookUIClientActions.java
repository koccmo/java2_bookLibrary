package team_VK.application.ui.Client_UI;

import team_VK.application.database.Database;
import team_VK.application.services.client_services.ShowBookService;

import java.util.Scanner;

public class ShowBookUIClientActions implements UIClientActions {

    private final ShowBookService showBookService;

    public ShowBookUIClientActions(ShowBookService showBookService) {
        this.showBookService = showBookService;
    }


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please selected book ID :");
        long showingBookID = scanner.nextLong();
        showBookService.showBook(showingBookID);
    }
}
