package book_library.application;

import java.util.Scanner;

public class DeleteBookByIdUICommand implements UICommand {

    private final ElectronicLibrary electronicLibrary;

    public DeleteBookByIdUICommand(ElectronicLibrary electronicLibrary) {
        this.electronicLibrary = electronicLibrary;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter book Id : ");
        Long bookId = scanner.nextLong();
        electronicLibrary.deleteBookById(bookId);

 //       System.out.println(!bookDeleted ? "\nSorry, book with Id " + bookId + " is not in electronic library" : "\nBook with Id " + bookId + " successfully deleted");
    }
}
