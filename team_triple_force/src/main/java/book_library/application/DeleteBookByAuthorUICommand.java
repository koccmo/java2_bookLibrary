package book_library.application;

import java.util.Scanner;

public class DeleteBookByAuthorUICommand implements UICommand {

    private final ElectronicLibrary electronicLibrary;

    public DeleteBookByAuthorUICommand(ElectronicLibrary electronicLibrary) {
        this.electronicLibrary = electronicLibrary;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deleting book from electronic library by author : ");
        System.out.print("Please enter book author : ");
        String bookTitle = scanner.nextLine();
        electronicLibrary.deleteBookByTitle(bookTitle);
        System.out.println("\nThe Book has been successfully deleted from to electronic library!");
        System.out.println("\nDeleted book author: " + bookTitle);
    }
}
