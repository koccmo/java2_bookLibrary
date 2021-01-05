package team_VK.application.ui.main_menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.core.services.main_menu_services.ShowBookService;
import team_VK.application.ui.UIActions;

import java.util.Scanner;
@Component
public class ShowBookUIActions implements UIActions {

    @Autowired
    private ShowBookService showBookService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please selected book ID :");
        long showingBookID = scanner.nextLong();
        showBookService.showBook(showingBookID);
    }
}
