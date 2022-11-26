package bookLibrary.console.ui;

import bookLibrary.core.request.FindByAuthorRequest;
import bookLibrary.core.response.FindByAuthorResponse;
import bookLibrary.core.service.FindBookByAuthorService;
import bookLibrary.dependency_injection.DIComponent;
import bookLibrary.dependency_injection.DIDependency;

import java.util.Scanner;
@DIComponent
public class FindBookByAuthorUIAction implements UIAction{
    @DIDependency private FindBookByAuthorService findBookBuyAuthor;


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
