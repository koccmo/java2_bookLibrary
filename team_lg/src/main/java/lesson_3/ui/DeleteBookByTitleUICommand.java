package lesson_3.ui;

import lesson_3.core.database.ElectronicLibrary;

import java.util.Scanner;

public class DeleteBookByTitleUICommand implements UICommand{

    private final ElectronicLibrary electronicLibrary;

    public DeleteBookByTitleUICommand(ElectronicLibrary electronicLibrary) {
        this.electronicLibrary = electronicLibrary;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deleting book from electronic library by title : ");
        System.out.print("Please enter book title : ");
        String bookTitle = scanner.nextLine();
        electronicLibrary.deleteBookByTitle(bookTitle);
        System.out.println("\nThe Book has been successfully deleted from to electronic library!");
        System.out.println("\nDeleted book title: " + bookTitle);
    }
}
