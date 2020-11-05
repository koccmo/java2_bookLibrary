package book_library.application;

import java.math.BigDecimal;
import java.util.Scanner;

public class AddBookUICommand implements UICommand {

    private final ElectronicLibrary electronicLibrary;

    public AddBookUICommand(ElectronicLibrary electronicLibrary) {
        this.electronicLibrary = electronicLibrary;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter book title: ");
        String bookTitle = scanner.nextLine();

        System.out.print("Please enter book author: ");
        String bookAuthor = scanner.nextLine();

        System.out.print("Please enter book price : ");
        BigDecimal bookPrice = scanner.nextBigDecimal();

        System.out.print("Please enter year of book issue : ");
        int yearOfBookIssue = scanner.nextInt();

        electronicLibrary.saveBook(new Book(bookTitle, bookAuthor, bookPrice, yearOfBookIssue));

        System.out.println("\nThe Book has been successfully added to electronic library!");
        System.out.println("Title: " + bookTitle + ", Author: " + bookAuthor + ", Year Of Book Issue: " + yearOfBookIssue + ", Price (EUR): " + bookPrice);
    }
}
