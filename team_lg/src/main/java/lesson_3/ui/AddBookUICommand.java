package lesson_3.ui;

import lesson_3.core.requests.AddBookRequest;
import lesson_3.core.responses.AddBookResponse;
import lesson_3.core.services.AddBookService;

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
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getErrorField() + " " + coreError.getErrorMessage())
            );
        } else {
            System.out.println("New book id was: " + response.getNewBook().getId());
            System.out.println("\nThe Book has been successfully added to electronic library!");
            System.out.println("Title: " + bookTitle + ", Author: " + bookAuthor + ", Year Of Book Issue: " + yearOfBookIssue + ", Price (EUR): " + bookPrice);

        }
    }
}
