package team_VK.application.ui;

import team_VK.application.core.services.DIDependency;
import team_VK.application.core.services.ShowBookService;
import team_VK.application.database.DIComponent;


import java.util.Scanner;
@DIComponent
public class ShowBookUIActions implements UIActions {

    @DIDependency private ShowBookService showBookService;

//    public ShowBookUIActions(ShowBookService showBookService) {
//        this.showBookService = showBookService;
//    }


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please selected book ID :");
        long showingBookID = scanner.nextLong();
        showBookService.showBook(showingBookID);
    }
}
