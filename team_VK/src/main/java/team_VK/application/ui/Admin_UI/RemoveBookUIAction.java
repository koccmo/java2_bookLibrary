package team_VK.application.ui.Admin_UI;

import team_VK.application.core.services.admin_services.RemoveBookService;

import java.util.Scanner;

public class RemoveBookUIAction implements UIActions {

    private final RemoveBookService removeBookService ;

    public RemoveBookUIAction(RemoveBookService removeBookService) {
        this.removeBookService = removeBookService;
    }





    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Book ID: ");
        long bookID = scanner.nextLong();
        removeBookService.removeBook(bookID);
        System.out.println("Your book was removed from list.");
    }
}
