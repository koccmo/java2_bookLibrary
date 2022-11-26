package bookLibrary.core.service;

import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.PrintAllBooksTitleRequest;
import bookLibrary.core.response.PrintAllBooksTitleResponse;
import bookLibrary.dependency_injection.DIComponent;
import bookLibrary.dependency_injection.DIDependency;

import java.util.List;
@DIComponent
public class PrintAllBookTitleService {
    @DIDependency private DataBase dataBase;


    public PrintAllBooksTitleResponse execute(PrintAllBooksTitleRequest printAllBooksRequest) {
        dataBase.getAllBooksTitle();
        List<String> titleList = dataBase.getAllBooksTitle();
        PrintAllBooksTitleResponse printAllBookResponse = new PrintAllBooksTitleResponse(titleList);
        return printAllBookResponse;
    }
}
