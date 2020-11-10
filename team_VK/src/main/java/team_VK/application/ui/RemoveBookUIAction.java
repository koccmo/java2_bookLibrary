package team_VK.application.ui;

import team_VK.application.services.RemoveBookService;

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
        String bookTitle = scanner.nextLine();
        System.out.println("Enter book author: ");
        String bookAuthor = scanner.nextLine();
        removeBookService.removeBook(bookTitle, bookAuthor);
        System.out.println("Your book was removed from list.");
    }
}
