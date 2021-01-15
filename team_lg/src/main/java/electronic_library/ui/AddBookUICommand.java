package electronic_library.ui;

import electronic_library.core.requests.AddBookRequest;
import electronic_library.core.responses.AddBookResponse;
import electronic_library.core.services.AddBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class AddBookUICommand implements UICommand {

    @Autowired
    private AddBookService addBookService;

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
