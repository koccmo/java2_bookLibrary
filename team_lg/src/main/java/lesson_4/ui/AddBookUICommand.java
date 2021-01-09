package lesson_4.ui;

import lesson_4.core.requests.AddBookRequest;
import lesson_4.core.responses.AddBookResponse;
import lesson_4.core.services.AddBookService;

import java.math.BigDecimal;
import java.util.Scanner;

public class AddBookUICommand implements UICommand {

    private AddBookService addBookService;

    public AddBookUICommand(AddBookService addBookService) {
        this.addBookService = addBookService;
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

        AddBookRequest request = new AddBookRequest(bookTitle, bookAuthor, bookPrice, yearOfBookIssue);
        AddBookResponse response = addBookService.execute(request);

        if (response.hasErrors()) {
            System.out.println("\n================== INCORRECT DATA ENTRY ======================");
            response.getErrors().forEach(System.out::println);
            System.out.println("==============================================================");
        } else {
            System.out.println("\n==============================================================");
            System.out.println("New book ID: " + response.getNewBook().getId());
            System.out.println("Book Title: " + bookTitle + ", Book Author: " + bookAuthor + ", Price (EUR): " + bookPrice + ", Year Of Book Issue: " + yearOfBookIssue);
            System.out.println("\nThe Book has been successfully added to electronic library!");
            System.out.println("==============================================================");
        }
    }
}
