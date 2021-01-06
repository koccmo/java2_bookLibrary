package book_library.console_ui;

import book_library.core.requests.GetAllBooksRequest;
import book_library.core.responses.GetAllBooksResponse;
import book_library.core.services.GetAllBooksService;
import book_library.dependency_injection.DIComponent;
import book_library.dependency_injection.DIDependency;

@DIComponent
public class GetAllBooksUIAction implements UIAction {

    @DIDependency GetAllBooksService getAllBooksService;

    @Override
    public void execute() {
        System.out.println("Book list:");
        System.out.println("***************************************************************************");
        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = getAllBooksService.execute(request);
        response.getBooks().forEach(System.out::println);
        System.out.println("***************************************************************************");
        System.out.println("Book list end.");
    }
}
