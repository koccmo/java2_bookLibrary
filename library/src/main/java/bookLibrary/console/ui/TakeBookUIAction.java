package bookLibrary.console.ui;

import bookLibrary.core.request.TakeBookRequest;
import bookLibrary.core.response.TakeBookResponse;
import bookLibrary.core.service.TakeBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Scanner;
@Component
public class TakeBookUIAction implements UIAction{
    @Autowired
    private TakeBookService service;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter reader ID");
        String readerId = scanner.nextLine();
        System.out.println("Enter book ID");
        String bookId = scanner.nextLine();
        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(formatter.format(date));
        TakeBookRequest request = new TakeBookRequest(readerId, bookId, date);
        TakeBookResponse response = service.execute(request);
        if (response.hasError()) {
            response.getErrors().forEach(System.out :: println);
        } else System.out.println("Book with ID " + bookId + " been token!");
    }
}
