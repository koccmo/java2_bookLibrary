package bookLibrary.console.ui;

import bookLibrary.core.request.FindByAuthorRequest;
import bookLibrary.core.response.FindByAuthorResponse;
import bookLibrary.core.service.FindBookByAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class FindBookByAuthorUIAction implements UIAction{
    @Autowired private FindBookByAuthorService findBookBuyAuthor;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book author for search :");
        String authorForSearch = scanner.nextLine();
        FindByAuthorRequest findByAuthorRequest = new FindByAuthorRequest(authorForSearch);
        System.out.println("Books with same Authors start :");
        FindByAuthorResponse findByAuthorResponse = findBookBuyAuthor.execute(findByAuthorRequest);
        findByAuthorResponse.getBookList().forEach(System.out::println);
        System.out.println("Book list finish!");
    }
}
