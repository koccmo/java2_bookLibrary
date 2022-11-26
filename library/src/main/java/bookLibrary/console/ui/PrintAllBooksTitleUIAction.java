package bookLibrary.console.ui;

import bookLibrary.core.request.PrintAllBooksTitleRequest;
import bookLibrary.core.response.PrintAllBooksTitleResponse;
import bookLibrary.core.service.PrintAllBookTitleService;
import bookLibrary.dependency_injection.DIComponent;
import bookLibrary.dependency_injection.DIDependency;

@DIComponent
public class PrintAllBooksTitleUIAction implements UIAction{
    @DIDependency private PrintAllBookTitleService printAllBookService;


    @Override
    public void execute() {
        System.out.println("Book list :");
        PrintAllBooksTitleRequest getAllBookTitle = new PrintAllBooksTitleRequest();
        PrintAllBooksTitleResponse getAllBooksTitleResponse = printAllBookService.execute(getAllBookTitle);
        getAllBooksTitleResponse.getBookList().forEach(System.out::println);
        System.out.println("Book list End!");
        System.out.println("");
    }
}
