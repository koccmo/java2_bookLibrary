package lesson_3.ui;

import lesson_3.core.database.ElectronicLibrary;

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
        Long id = Long.parseLong(scanner.nextLine());
        electronicLibrary.deleteBookById(id);
       System.out.println("\nBook with Id=" + id + " successfully deleted!");
    }
}
