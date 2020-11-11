package team_VK.application.ui;

import team_VK.application.services.AddBookService;

import java.util.Scanner;


public class AddBookUIAction implements UIActions {

    private final AddBookService addBookService;

    public AddBookUIAction(AddBookService addBookService) {
        this.addBookService = addBookService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter book Title :");
        String bookTitle = scanner.nextLine();
        System.out.println("Please enter book Author :");
        String bookAuthor = scanner.nextLine();
        addBookService.addBook(bookTitle, bookAuthor);
        System.out.println("Your book was added to list.");
        System.out.println();
    }
}
