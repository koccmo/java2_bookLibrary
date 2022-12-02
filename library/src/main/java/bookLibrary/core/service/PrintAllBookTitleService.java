package bookLibrary.core.service;

import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.PrintAllBooksTitleRequest;
import bookLibrary.core.response.PrintAllBooksTitleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PrintAllBookTitleService {
    @Autowired
    private DataBase dataBase;


    public PrintAllBooksTitleResponse execute(PrintAllBooksTitleRequest printAllBooksRequest) {
        dataBase.getAllBooksTitle();
        List<String> titleList = dataBase.getAllBooksTitle();
        PrintAllBooksTitleResponse printAllBookResponse = new PrintAllBooksTitleResponse(titleList);
        return printAllBookResponse;
    }
}
